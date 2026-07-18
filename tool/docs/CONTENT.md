# Content buckets

Every rotating daily feature (Poem, Excerpt, Philosophy, Morning Prompt, Joke,
Trivia, Today in History) is backed by a plain Kotlin file, `Trinkets*.kt`,
in [`tool/src/main/kotlin/com/thelightphone/trinkets/`](../src/main/kotlin/com/thelightphone/trinkets/)
containing a `List<...>` of that bucket's items. There's no JSON, no
network call, and no bundled asset file. The Light SDK sandbox blocks
`LocalContext.current` and similar Activity/Context access (see the SDK's
own lint rules), so a tool can't read files out of its own `assets/`
folder at runtime. Embedding the data directly in Kotlin sidesteps that
restriction entirely.

## Current bucket sizes

| Bucket | File | Count today |
|---|---|---|
| Poems | `TrinketsPoems.kt` | 360 |
| Literary excerpts | `TrinketsExcerpts.kt` | 131 (see note below, this one has a hard ceiling that isn't just about effort) |
| Philosophy prompts | `TrinketsPhilosophyPrompts.kt` | 360 |
| Morning prompts | `TrinketsMorningPrompts.kt` | 360 (120 gentle / 120 steady / 120 energizing) |
| Jokes | `TrinketsJokes.kt` | 360 |
| Trivia | `TrinketsTrivia.kt` | 360 |
| Today in History | `TrinketsHistoryFacts.kt` | 360 facts across ~200 distinct dates |

At 360 items, every bucket except Excerpts rotates through a full year
without a single repeat (the daily pick is `epochDay % bucketSize`, so 360
distinct items means 360 distinct days before the cycle repeats).

## Why Excerpts is capped well under 360

Poems, Jokes, Trivia, Philosophy, and Morning Prompts are all original
writing produced for Trinkets, nothing in those buckets is reproduced
from an external source, so there's no ceiling on how large they can get
beyond the effort of writing more.

Excerpts is different by design: each entry is a short (under 15 words),
verifiably public-domain quote from a real literary work: a poem, a
novel, a philosophical text, properly attributed to its author and
source, rather than original writing. Two hard rules apply here that
don't apply to the other buckets:

1. **Every quote stays under 15 words.** This is a strict, non-negotiable
   ceiling regardless of the source's copyright status.
2. **Each source work is used at most once across the entire bucket.**
   Meditations by Marcus Aurelius can supply exactly one quote, not
   thirty, even though the book obviously contains many more quotable
   lines.

Rule 2 is the real constraint on scale: growing Excerpts means finding
*another distinct public-domain work* for every new entry, not just
writing more content from a source already in use. That's real research,
not just more writing, so it grows far more slowly than the other
buckets. No quotes are drawn from Abrahamic scripture or religious
tradition (Bible, Quran, Torah, Talmud), per an explicit content
decision. Asian religious and philosophical texts (Tao Te Ching,
Analects, Dhammapada, Bhagavad Gita, Zhuangzi) are in scope and are
quoted from old, specifically public-domain English translations (James
Legge, Max Muller, Edwin Arnold, Elizabeth Carter), never a modern
copyrighted translation. A handful of entries are quoted in their
original French, Spanish, Portuguese, or Mandarin Chinese rather than in
translation, since the original text avoids any translation-copyright
question entirely. Lyric poetry (even public-domain poetry) is excluded
from this bucket too, for the same reason the Poems bucket is original
writing: reproducing a poem, even briefly, isn't something this content
bank does. Only prose, drama, philosophy, and speeches are used.

**A note on accuracy:** the current 52 entries were checked individually
for real, verifiable provenance. A small number of entries drafted
earlier in this bucket's development turned out to be either misattributed
("fake quote" internet lore, common with Confucius/Buddha/Einstein/Gandhi
quotes) or, in a few cases, actually still under copyright (Tolkien,
Orwell, Plath, Rowling were mistakenly included at one point and then
removed). Anyone adding to this bucket should hold new entries to the
same standard: a real, checkable source, not just something widely
repeated online as a quote from someone.

## How the daily rotation works

`ContentRepository` (in `ContentModels.kt`) picks a deterministic "item of
the day" per bucket using `LocalDate.now().toEpochDay() % bucket.size`, with
a different fixed offset per category so Poem, Joke, Trivia, etc. don't all
roll over on the same day. Same calendar day always shows the same item on
a given device, and nothing repeats until the entire bucket has cycled
through.

Today in History is different: it's keyed by the real calendar month/day
(`HistoryFact.month` / `HistoryFact.day`), not by rotation, since the whole
point is showing what actually happened on today's date. A given date can
have zero, one, or several facts. Facts were chosen for accuracy and for
global spread: Africa, Asia, the Americas, the Middle East, and Oceania
are all deliberately represented, not just Europe and North America.

## Growing a bucket

1. Open the relevant `Trinkets*.kt` file.
2. Add new entries with the next sequential `id`.
3. Before committing, check for accidental duplicates, e.g.:
   ```bash
   # from tool/src/main/kotlin/com/thelightphone/trinkets
   grep -oP '(?<=Joke\(\d, ")[^"]+' TrinketsJokes.kt | sort | uniq -d
   ```
4. For **Today in History**, only add facts you can verify, no invented or
   approximated events.
5. For **Excerpts**, verify the quote's exact wording and real source
   before adding it (a web search against the actual text, not just a
   quote-aggregator site), confirm it's under 15 words, confirm the
   specific work hasn't already supplied an entry, and confirm the English
   translation used (if any) is itself old enough to be public domain.
6. Rebuild. No schema or code changes are needed to add more entries to
   an existing bucket, only more list items.
