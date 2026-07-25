package com.tyshi00.trinkets

import java.time.LocalDate

// === Content models ===
// All copy in these buckets is original writing produced for this app (or,
// for Today in History, original wording describing real, well-documented
// historical facts), nothing here is reproduced from a third-party
// copyrighted source. Data lives in the generated Trinkets*Content.kt files
// as plain Kotlin lists (the Light SDK sandbox doesn't expose Context, so
// this app can't read bundled JSON/asset files. See docs/design_decisions
// in the SDK root for the LocalContext restriction).

data class Poem(val id: Int, val title: String, val body: String)

/**
 * A short, verifiably public-domain quote (under 15 words) from a real
 * literary work, properly attributed. Unlike Poems, which are original
 * writing for this app, Excerpts are meant to be genuine tidbits of
 * existing literature. See docs/CONTENT.md for sourcing rules.
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
data class HistoryFact(val id: Int, val month: Int, val day: Int, val year: Int, val region: String, val event: String)

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
    fun jokeOfTheDay(): Joke? = pickOfTheDay(TrinketsJokes.ALL, categoryOffset = 271)
    fun triviaOfTheDay(): TriviaItem? = pickOfTheDay(TrinketsTrivia.ALL, categoryOffset = 409)
    fun philosophyPromptOfTheDay(): PhilosophyPrompt? = pickOfTheDay(TrinketsPhilosophyPrompts.ALL, categoryOffset = 563)

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
