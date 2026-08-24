package com.tyshi00.trinkets

const val MAX_COUNTDOWNS = 7

/** How countdown dates are displayed throughout the app. */
enum class DateFormat(val label: String) {
    MDY("mm/dd/yyyy"),
    DMY("dd/mm/yyyy"),
    YMD("yyyy/mm/dd"),
}

/** 12-hour (AM/PM) vs 24-hour clock display, used anywhere a specific time of day is shown. */
enum class TimeFormat(val label: String) {
    AM_PM("AM/PM"),
    HOUR_24("24-hour"),
}

/** Which feature the Home screen shows by default. */
enum class HomeDefault(val label: String) {
    COUNTDOWN("Countdowns"),
    POEM("Poem of the Day"),
    EXCERPT("Literary Excerpt"),
    HISTORY("Today in History"),
    REFLECTION("Reflection"),
    PHILOSOPHY("Philosophy Prompt"),
    MORNING("Morning Prompt"),
    JOKE("Joke of the Day"),
    TRIVIA("Trivia"),
}

/**
 * A slot choice for the split Home screen. Mirrors [HomeDefault] (Countdowns
 * plus every content feature) and can be used for either the primary (top) or
 * secondary (bottom) half.
 */
enum class SplitSlot(val label: String) {
    COUNTDOWN("Countdowns"),
    POEM("Poem of the Day"),
    EXCERPT("Literary Excerpt"),
    HISTORY("Today in History"),
    REFLECTION("Reflection"),
    PHILOSOPHY("Philosophy Prompt"),
    MORNING("Morning Prompt"),
    JOKE("Joke of the Day"),
    TRIVIA("Trivia");

    /** The matching content feature, or null for Countdowns which isn't a rotating feature. */
    fun toFeature(): TrinketsFeature? = when (this) {
        COUNTDOWN -> null
        POEM -> TrinketsFeature.POEM
        EXCERPT -> TrinketsFeature.EXCERPT
        HISTORY -> TrinketsFeature.HISTORY
        REFLECTION -> TrinketsFeature.REFLECTION
        PHILOSOPHY -> TrinketsFeature.PHILOSOPHY
        MORNING -> TrinketsFeature.MORNING
        JOKE -> TrinketsFeature.JOKE
        TRIVIA -> TrinketsFeature.TRIVIA
    }
}

/** Every rotating content feature, used for the feature list screen and the settings on/off toggles. */
enum class TrinketsFeature(val label: String) {
    POEM("Poem of the Day"),
    EXCERPT("Literary Excerpt"),
    HISTORY("Today in History"),
    REFLECTION("Reflection"),
    PHILOSOPHY("Philosophy Prompt"),
    MORNING("Morning Prompt"),
    JOKE("Joke of the Day"),
    TRIVIA("Trivia"),
}

data class FeatureVisibility(
    val poemEnabled: Boolean = true,
    val excerptEnabled: Boolean = true,
    val historyEnabled: Boolean = true,
    val philosophyEnabled: Boolean = true,
    val morningEnabled: Boolean = true,
    val jokeEnabled: Boolean = true,
    val triviaEnabled: Boolean = true,
    val reflectionEnabled: Boolean = true,
) {
    fun isEnabled(feature: TrinketsFeature): Boolean = when (feature) {
        TrinketsFeature.POEM -> poemEnabled
        TrinketsFeature.EXCERPT -> excerptEnabled
        TrinketsFeature.HISTORY -> historyEnabled
        TrinketsFeature.REFLECTION -> reflectionEnabled
        TrinketsFeature.PHILOSOPHY -> philosophyEnabled
        TrinketsFeature.MORNING -> morningEnabled
        TrinketsFeature.JOKE -> jokeEnabled
        TrinketsFeature.TRIVIA -> triviaEnabled
    }
}

class TrinketsRepository(private val db: TrinketsDatabase) {

    companion object {
        private const val PREF_INVERT = "invert_colors"
        private const val PREF_HOME_DEFAULT = "home_default"
        private const val PREF_MOTIVATION_INTENSITY = "motivation_intensity"
        private const val PREF_POEM_ENABLED = "poem_enabled"
        private const val PREF_EXCERPT_ENABLED = "excerpt_enabled"
        private const val PREF_HISTORY_ENABLED = "history_enabled"
        private const val PREF_PHILOSOPHY_ENABLED = "philosophy_enabled"
        private const val PREF_MORNING_ENABLED = "morning_enabled"
        private const val PREF_JOKE_ENABLED = "joke_enabled"
        private const val PREF_TRIVIA_ENABLED = "trivia_enabled"
        private const val PREF_REFLECTION_ENABLED = "reflection_enabled"
        private const val PREF_REFLECTION_USED = "reflection_used_ids"
        private const val PREF_COUNTDOWN_TIMER_ENABLED = "countdown_timer_enabled"
        private const val PREF_DATE_FORMAT = "date_format"
        private const val PREF_TIME_FORMAT = "time_format"
        private const val PREF_SPLIT_HOME_ENABLED = "split_home_enabled"
        private const val PREF_SPLIT_PRIMARY = "split_primary"
        private const val PREF_SPLIT_SECONDARY = "split_secondary"
        private const val PREF_FEATURED_COUNTDOWN = "featured_countdown_id"

        @Volatile private var INSTANCE: TrinketsRepository? = null

        fun getInstance(factory: () -> TrinketsDatabase): TrinketsRepository =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: TrinketsRepository(factory()).also { INSTANCE = it }
            }
    }

    // === Countdowns ===
    suspend fun getCountdowns(): List<CountdownEntry> = db.countdownDao().getAll()

    /** Returns false (and inserts nothing) if [MAX_COUNTDOWNS] has already been reached. */
    suspend fun addCountdown(name: String, date: String, notes: String?): Boolean {
        if (db.countdownDao().count() >= MAX_COUNTDOWNS) return false
        db.countdownDao().insert(CountdownEntry(name = name, date = date, notes = notes?.ifBlank { null }))
        return true
    }

    suspend fun deleteCountdown(id: Long) = db.countdownDao().deleteById(id)

    suspend fun updateCountdown(id: Long, name: String, date: String, notes: String?) =
        db.countdownDao().update(id, name, date, notes?.ifBlank { null })

    suspend fun canAddCountdown(): Boolean = db.countdownDao().count() < MAX_COUNTDOWNS

    // === Display / theme ===
    suspend fun getInvertColors(): Boolean = db.preferenceDao().get(PREF_INVERT)?.value == "true"

    suspend fun setInvertColors(value: Boolean) =
        db.preferenceDao().set(PreferenceEntry(PREF_INVERT, value.toString()))

    suspend fun getHomeDefault(): HomeDefault {
        val raw = db.preferenceDao().get(PREF_HOME_DEFAULT)?.value ?: return HomeDefault.COUNTDOWN
        return HomeDefault.entries.firstOrNull { it.name == raw } ?: HomeDefault.COUNTDOWN
    }

    suspend fun setHomeDefault(value: HomeDefault) =
        db.preferenceDao().set(PreferenceEntry(PREF_HOME_DEFAULT, value.name))

    // === Split Home screen ===
    /**
     * When enabled, Home shows two features stacked vertically (primary on top,
     * secondary beneath) instead of the single [getHomeDefault] feature.
     */
    suspend fun getSplitHomeEnabled(): Boolean =
        db.preferenceDao().get(PREF_SPLIT_HOME_ENABLED)?.value == "true"

    suspend fun setSplitHomeEnabled(value: Boolean) =
        db.preferenceDao().set(PreferenceEntry(PREF_SPLIT_HOME_ENABLED, value.toString()))

    suspend fun getSplitPrimary(): SplitSlot {
        val raw = db.preferenceDao().get(PREF_SPLIT_PRIMARY)?.value ?: return SplitSlot.COUNTDOWN
        return SplitSlot.entries.firstOrNull { it.name == raw } ?: SplitSlot.COUNTDOWN
    }

    suspend fun setSplitPrimary(value: SplitSlot) =
        db.preferenceDao().set(PreferenceEntry(PREF_SPLIT_PRIMARY, value.name))

    suspend fun getSplitSecondary(): SplitSlot {
        val raw = db.preferenceDao().get(PREF_SPLIT_SECONDARY)?.value ?: return SplitSlot.MORNING
        return SplitSlot.entries.firstOrNull { it.name == raw } ?: SplitSlot.MORNING
    }

    suspend fun setSplitSecondary(value: SplitSlot) =
        db.preferenceDao().set(PreferenceEntry(PREF_SPLIT_SECONDARY, value.name))

    // === Featured countdown ===
    /**
     * Which countdown to show when Home (or a split slot) displays Countdowns.
     * Null means "no explicit pick", so callers fall back to the soonest upcoming.
     * Stored as a preference rather than a column on the entry so that starring
     * doesn't require a Room schema migration.
     */
    suspend fun getFeaturedCountdownId(): Long? =
        db.preferenceDao().get(PREF_FEATURED_COUNTDOWN)?.value?.toLongOrNull()

    /** Pass null to clear the pick and fall back to the soonest upcoming countdown. */
    suspend fun setFeaturedCountdownId(id: Long?) =
        db.preferenceDao().set(PreferenceEntry(PREF_FEATURED_COUNTDOWN, id?.toString() ?: ""))

    // === Countdown timer + formats ===
    suspend fun getCountdownTimerEnabled(): Boolean =
        db.preferenceDao().get(PREF_COUNTDOWN_TIMER_ENABLED)?.value == "true"

    suspend fun setCountdownTimerEnabled(value: Boolean) =
        db.preferenceDao().set(PreferenceEntry(PREF_COUNTDOWN_TIMER_ENABLED, value.toString()))

    suspend fun getDateFormat(): DateFormat {
        val raw = db.preferenceDao().get(PREF_DATE_FORMAT)?.value ?: return DateFormat.MDY
        return DateFormat.entries.firstOrNull { it.name == raw } ?: DateFormat.MDY
    }

    suspend fun setDateFormat(value: DateFormat) =
        db.preferenceDao().set(PreferenceEntry(PREF_DATE_FORMAT, value.name))

    suspend fun getTimeFormat(): TimeFormat {
        val raw = db.preferenceDao().get(PREF_TIME_FORMAT)?.value ?: return TimeFormat.AM_PM
        return TimeFormat.entries.firstOrNull { it.name == raw } ?: TimeFormat.AM_PM
    }

    suspend fun setTimeFormat(value: TimeFormat) =
        db.preferenceDao().set(PreferenceEntry(PREF_TIME_FORMAT, value.name))

    // === Motivation intensity preference ===
    /** Null means "any intensity." Morning Prompt rotates across the full range. */
    suspend fun getMotivationIntensity(): MotivationIntensity? {
        val raw = db.preferenceDao().get(PREF_MOTIVATION_INTENSITY)?.value ?: return null
        return MotivationIntensity.entries.firstOrNull { it.name == raw }
    }

    suspend fun setMotivationIntensity(value: MotivationIntensity?) =
        db.preferenceDao().set(PreferenceEntry(PREF_MOTIVATION_INTENSITY, value?.name ?: ""))

    // === Feature on/off ===
    suspend fun getFeatureVisibility(): FeatureVisibility = FeatureVisibility(
        poemEnabled = db.preferenceDao().get(PREF_POEM_ENABLED)?.value != "false",
        excerptEnabled = db.preferenceDao().get(PREF_EXCERPT_ENABLED)?.value != "false",
        historyEnabled = db.preferenceDao().get(PREF_HISTORY_ENABLED)?.value != "false",
        philosophyEnabled = db.preferenceDao().get(PREF_PHILOSOPHY_ENABLED)?.value != "false",
        morningEnabled = db.preferenceDao().get(PREF_MORNING_ENABLED)?.value != "false",
        jokeEnabled = db.preferenceDao().get(PREF_JOKE_ENABLED)?.value != "false",
        triviaEnabled = db.preferenceDao().get(PREF_TRIVIA_ENABLED)?.value != "false",
        reflectionEnabled = db.preferenceDao().get(PREF_REFLECTION_ENABLED)?.value != "false",
    )

    suspend fun setFeatureEnabled(feature: TrinketsFeature, enabled: Boolean) {
        val key = when (feature) {
            TrinketsFeature.POEM -> PREF_POEM_ENABLED
            TrinketsFeature.EXCERPT -> PREF_EXCERPT_ENABLED
            TrinketsFeature.HISTORY -> PREF_HISTORY_ENABLED
            TrinketsFeature.PHILOSOPHY -> PREF_PHILOSOPHY_ENABLED
            TrinketsFeature.MORNING -> PREF_MORNING_ENABLED
            TrinketsFeature.JOKE -> PREF_JOKE_ENABLED
            TrinketsFeature.TRIVIA -> PREF_TRIVIA_ENABLED
            TrinketsFeature.REFLECTION -> PREF_REFLECTION_ENABLED
        }
        db.preferenceDao().set(PreferenceEntry(key, enabled.toString()))
        // If the currently-hidden feature was the Home default, Home screen
        // logic falls back to Countdowns rather than showing a dead screen,
        // handled in HomeViewModel.reload() by re-checking visibility.
    }

    // === Reset ===
    // === Reflection: used prompts ===
    /**
     * Ids the person has checked off. Daily sets are drawn from the unused
     * prompts first, so the whole pool cycles through before anything repeats.
     * Stored as a comma separated string in the existing preference table,
     * which avoids a Room schema migration.
     */
    suspend fun getUsedReflectionIds(): Set<Int> {
        val raw = db.preferenceDao().get(PREF_REFLECTION_USED)?.value.orEmpty()
        if (raw.isBlank()) return emptySet()
        return raw.split(",").mapNotNull { it.trim().toIntOrNull() }.toSet()
    }

    suspend fun setUsedReflectionIds(ids: Set<Int>) {
        db.preferenceDao().set(PreferenceEntry(PREF_REFLECTION_USED, ids.sorted().joinToString(",")))
    }

    /** Checks a prompt off, or un-checks it if it was already marked used. */
    suspend fun toggleReflectionUsed(id: Int) {
        val current = getUsedReflectionIds()
        setUsedReflectionIds(if (id in current) current - id else current + id)
    }

    suspend fun clearUsedReflectionIds() = setUsedReflectionIds(emptySet())

    suspend fun resetAll() {
        db.countdownDao().resetAll()
        db.preferenceDao().resetAll()
    }
}
