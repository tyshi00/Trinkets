package com.tyshi00.trinkets

import java.time.LocalDate

// === Content models ===
// Most buckets are original writing for this app. Two are not: Literary
// Excerpts are short attributed quotations, and Poems also carries a set of
// public-domain poems alongside the originals. Data lives in plain Kotlin
// lists because the Light SDK sandbox doesn't expose Context, so the app
// can't read bundled JSON/asset files. See docs/CONTENT.md.

/** author is null for the app's own poems, set for public-domain works. */
data class Poem(val id: Int, val title: String, val body: String, val author: String? = null)

/**
 * A short attributed quote (under 15 words) from a real literary work. Most
 * are public domain; some are brief quotations from modern works. See
 * docs/CONTENT.md for sourcing rules.
 */
data class Excerpt(val id: Int, val quote: String, val author: String, val work: String)

enum class MotivationIntensity(val label: String) {
    GENTLE("Gentle"),
    STEADY("Steady"),
    ENERGIZING("Energizing"),
}

data class MorningPrompt(val id: Int, val text: String, val intensity: MotivationIntensity)

data class PhilosophyPrompt(val id: Int, val prompt: String, val question: String)

data class Joke(val id: Int, val setup: String, val punchline: String)

data class TriviaItem(val id: Int, val question: String, val answer: String)

/** One fact tied to a specific real month/day, drawn from global history. */
data class HistoryFact(val id: Int, val month: Int, val day: Int, val year: Int, val region: String, val event: String) {
    /**
     * Year as shown to the reader. Anything before the year 1000 gets an
     * explicit "CE" so a fact from the year 30 doesn't read as a bare number
     * that looks like an error. Modern years are left plain.
     */
    val yearLabel: String get() = if (year < 1000) "$year CE" else "$year"

    /** Full heading line, e.g. "30 CE, Africa" or "1969, North America". */
    val heading: String get() = "$yearLabel, $region"
}

/**
 * Picks an "item of the day" per category from an epoch-day index. Same day
 * always shows the same item, and nothing repeats until the whole bucket has
 * cycled through (which, at bucket size N, takes N days).
 *
 * Today in History is keyed by real calendar month/day instead, since it's
 * meant to reflect what actually happened on this date.
 */
object ContentRepository {

    private fun epochDay(): Long = LocalDate.now().toEpochDay()

    /** categoryOffset spreads different categories apart so they don't all roll over on the same day. */
    private fun <T> pickOfTheDay(items: List<T>, categoryOffset: Long): T? {
        if (items.isEmpty()) return null
        val index = ((epochDay() + categoryOffset) % items.size + items.size) % items.size
        return items[index.toInt()]
    }

    fun poemOfTheDay(): Poem? = pickOfTheDay(TrinketsPoems.ALL, categoryOffset = 0)
    fun excerptOfTheDay(): Excerpt? = pickOfTheDay(TrinketsExcerpts.ALL, categoryOffset = 137)
    /**
     * Jokes pinned to a specific calendar date, keyed by month to day. These
     * always win over the normal rotation, so a joke tied to a date shows up
     * every year on that date.
     */
    private val PINNED_JOKES: Map<Pair<Int, Int>, Int> = mapOf(
        // August 22: scuba joke, by request.
        (8 to 22) to TrinketsJokes.SCUBA_JOKE_ID,
    )

    fun jokeOfTheDay(): Joke? {
        val today = LocalDate.now()
        PINNED_JOKES[today.monthValue to today.dayOfMonth]?.let { pinnedId ->
            TrinketsJokes.ALL.firstOrNull { it.id == pinnedId }?.let { return it }
        }
        return pickOfTheDay(TrinketsJokes.ALL, categoryOffset = 271)
    }
    fun triviaOfTheDay(): TriviaItem? = pickOfTheDay(TrinketsTrivia.ALL, categoryOffset = 409)
    fun philosophyPromptOfTheDay(): PhilosophyPrompt? = pickOfTheDay(TrinketsPhilosophyPrompts.ALL, categoryOffset = 563)

    /** How many Reflection prompts are offered each day. */
    const val REFLECTION_DAILY_COUNT = 3

    /**
     * Today's Reflection prompts, drawn from the unused pool first so the whole
     * bucket cycles before anything repeats. When fewer than
     * [REFLECTION_DAILY_COUNT] unused prompts remain, the rest are topped up
     * from the used ones so a full set always shows.
     *
     * The draw is seeded on the date, so it's stable across the day and across
     * app restarts, but shifts to a new set tomorrow.
     */
    fun reflectionPromptsForToday(usedIds: Set<Int> = emptySet()): List<ReflectionPrompt> {
        val all = TrinketsReflectionPrompts.ALL
        if (all.isEmpty()) return emptyList()

        val seed = epochDay()
        val unused = all.filterNot { it.id in usedIds }
        val chosen = unused.rotatedBy(seed).take(REFLECTION_DAILY_COUNT)
        if (chosen.size >= REFLECTION_DAILY_COUNT) return chosen

        // Everything (or nearly everything) has been checked off: top up from
        // the already-used prompts rather than showing a short set.
        val fallback = all.filter { it.id in usedIds }
            .rotatedBy(seed)
            .filterNot { p -> chosen.any { it.id == p.id } }
            .take(REFLECTION_DAILY_COUNT - chosen.size)
        return chosen + fallback
    }

    /**
     * Deterministic rotation: same list and seed always give the same order,
     * without needing a shuffle whose implementation could change between
     * Kotlin versions.
     */
    private fun <T> List<T>.rotatedBy(seed: Long): List<T> {
        if (isEmpty()) return this
        val start = ((seed % size) + size).toInt() % size
        return subList(start, size) + subList(0, start)
    }

    /**
     * If a preferred intensity is set, cycles day by day through just that
     * intensity's prompts. If "Any" (null), rotates which intensity shows
     * *each day* (Gentle, Steady, Energizing, repeating) so a short testing
     * window actually sees variety, rather than walking straight through
     * the underlying list, which happens to be stored in same-intensity
     * blocks of 30. Either way, every prompt of the relevant intensity gets
     * shown exactly once before any repeat.
     */
    fun morningPromptOfTheDay(preferredIntensity: MotivationIntensity?): MorningPrompt? {
        if (preferredIntensity != null) {
            val pool = TrinketsMorningPrompts.ALL.filter { it.intensity == preferredIntensity }
                .ifEmpty { TrinketsMorningPrompts.ALL }
            return pickOfTheDay(pool, categoryOffset = 701)
        }
        val day = epochDay()
        val intensities = MotivationIntensity.entries
        val slotCount = intensities.size.toLong()
        val todaysIntensity = intensities[(((day % slotCount) + slotCount) % slotCount).toInt()]
        val pool = TrinketsMorningPrompts.ALL.filter { it.intensity == todaysIntensity }
        if (pool.isEmpty()) return null
        // Advances by one only every [slotCount] real days (this intensity's
        // turn), so across a full rotation it still reaches every prompt of
        // today's intensity rather than skipping most of them.
        val turnIndex = Math.floorDiv(day, slotCount) + 701
        val subIndex = ((turnIndex % pool.size) + pool.size) % pool.size
        return pool[subIndex.toInt()]
    }

    fun historyFactsForToday(): List<HistoryFact> {
        val today = LocalDate.now()
        return TrinketsHistoryFacts.ALL
            .filter { it.month == today.monthValue && it.day == today.dayOfMonth }
            .sortedBy { it.year }
    }

    /**
     * Some calendar dates have several logged facts. Rather than show them
     * all at once, pick just one per day, rotating by the current year so a
     * date with N facts cycles through all N before repeating (instead of
     * showing the same first fact every year). This also means new facts
     * can keep being added to a date over time without ever crowding the
     * display; they just join that date's rotation.
     */
    fun historyFactOfTheDay(): HistoryFact? {
        val candidates = historyFactsForToday()
        if (candidates.isEmpty()) return null
        val today = LocalDate.now()
        val index = ((today.year % candidates.size) + candidates.size) % candidates.size
        return candidates[index]
    }

    fun bucketSizes(): Map<String, Int> = mapOf(
        "Poems" to TrinketsPoems.ALL.size,
        "Literary excerpts" to TrinketsExcerpts.ALL.size,
        "Morning prompts" to TrinketsMorningPrompts.ALL.size,
        "Philosophy prompts" to TrinketsPhilosophyPrompts.ALL.size,
        "Jokes" to TrinketsJokes.ALL.size,
        "Trivia" to TrinketsTrivia.ALL.size,
        "Today in History facts" to TrinketsHistoryFacts.ALL.size,
    )
}
