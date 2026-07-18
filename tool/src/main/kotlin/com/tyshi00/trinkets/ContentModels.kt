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

    /** If a preferred intensity is set, only rotates through prompts at that intensity. */
    fun morningPromptOfTheDay(preferredIntensity: MotivationIntensity?): MorningPrompt? {
        val pool = if (preferredIntensity == null) {
            TrinketsMorningPrompts.ALL
        } else {
            TrinketsMorningPrompts.ALL.filter { it.intensity == preferredIntensity }.ifEmpty { TrinketsMorningPrompts.ALL }
        }
        return pickOfTheDay(pool, categoryOffset = 701)
    }

    fun historyFactsForToday(): List<HistoryFact> {
        val today = LocalDate.now()
        return TrinketsHistoryFacts.ALL
            .filter { it.month == today.monthValue && it.day == today.dayOfMonth }
            .sortedBy { it.year }
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
