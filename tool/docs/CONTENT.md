# Content buckets

Every rotating daily feature (Poem, Excerpt, Philosophy, Morning Prompt, Joke,
Trivia, Today in History) is backed by a plain Kotlin file, `Trinkets*.kt`,
in [`tool/src/main/kotlin/com/tyshi00/trinkets/`](../src/main/kotlin/com/tyshi00/trinkets/)
containing a `List<...>` of that bucket's items. There's no JSON, no
network call, and no bundled asset file: the Light SDK sandbox blocks
Activity/Context access, so the tool can't read its own `assets/` folder
at runtime, and embedding the data in Kotlin sidesteps that.

## Current bucket sizes

| Bucket | File | Count today |
|---|---|---|
| Poems | `TrinketsPoems.kt` | 133 (14 original + 119 public-domain; see note below) |
| Philosophy prompts | `TrinketsPhilosophyPrompts.kt` | 360 |
| Morning prompts | `TrinketsMorningPrompts.kt` | 360 (120 gentle / 120 steady / 120 energizing) |
| Trivia | `TrinketsTrivia.kt` | 360 |
| Today in History | `TrinketsHistoryFacts.kt` | 582 facts across 347 distinct dates |
| Literary excerpts | `TrinketsExcerpts.kt` | 328 (see note below, this one has a hard ceiling that isn't just about effort) |
| Jokes | `TrinketsJokes.kt` | 207 |
| Reflection prompts | `TrinketsReflectionPrompts.kt` | 94 (three offered per day, see below) |

At 360 items, a bucket rotates through a full year without a single repeat
(the daily pick is `epochDay % bucketSize`, so 360 distinct items means 360
distinct days before the cycle repeats). Philosophy, morning prompts, and
trivia are all there.

Poems, excerpts, jokes, and Reflection sit below that line for reasons that
aren't just effort: Poems now mixes a few originals with public-domain poems
that have to be found and verified one at a time (see below), excerpts and
jokes have to be real attributable things rather than filler, and Reflection
is newer. Today in History runs above 360 because it's keyed to actual
calendar dates rather than a flat rotation, so a date with several notable
events holds several facts.

## Reflection: a different rotation

Reflection doesn't use `epochDay % bucketSize`. It offers three prompts a day,
drawn from the ones you haven't checked off yet, so the whole pool cycles
before anything comes back. Checking a prompt off (the circle button) removes
it from future draws; checking it again puts it back. When fewer than three
unused prompts remain, the set is topped up from used ones so a full three
always show. The daily draw is seeded on the date, so it's stable across the
day and across app restarts.

The prompts are adapted from Light Phone's Reflect tool. Reflect grouped them
into nine categories with a filter screen; Trinkets drops the categories and
draws from the whole pool. See the credits section in the root README for
licence details.

## Jokes: two formats

Most jokes are setup and punchline. One-liners that have no natural question
borrow one of a small set of rotating openers ("Wanna hear a joke?", "Up for a
laugh?", and so on) so the tap-to-reveal screen still has something to
withhold. Keep that set small, around five to seven, so the openers stay
familiar rather than feeling random.

A joke can also be pinned to a specific calendar date via `PINNED_JOKES` in
`ContentModels.kt`, which overrides the normal rotation for that day.

## Poems: originals plus public-domain poems

Fourteen poems are original writing for Trinkets (`author` is null, except
the four with the pseudonymous byline "MVt"). The rest are real poems
reproduced in full, each with its `author` set. Rules:

- **Public domain only.** The poem's text must be public domain in the US
  (author long dead, or first published before 1929). Contemporary poems
  are never reproduced in full here; a single attributed line can go in
  Excerpts instead.
- **No devotional or overtly religious poems.** Nothing that is a hymn or
  prayer, or whose subject is God, Christ, or faith. Incidental references
  ("Eden", "whatever gods may be") are fine. This is why Hopkins, Donne,
  Herbert, and Vaughan are not here.
- **No bigots.** Authors with well-documented records of racism,
  antisemitism, homophobia, transphobia, or similar are excluded, however
  celebrated. This is why Kipling, Yeats, Whitman, Larkin, Burns, Pound,
  and Eliot are not here.
- Verify the exact text against a reliable source, not a quote-aggregator.
- Poem text renders left-aligned, so line breaks and indentation in the
  raw strings are shown as written.

Growing this bucket is slow: every addition is a separate poem to find,
vet, and transcribe. A ~180-day (six-month) rotation needs ~180 entries.

## Why Excerpts is capped well under 360

Trivia, Philosophy, and Morning Prompts are all original writing produced
for Trinkets, nothing in those buckets is reproduced from an external
source, so there's no ceiling on how large they can get beyond the effort
of writing more.

Jokes sit in between. Traditional dad jokes are folk humour with no
identifiable author and circulate freely, so they're fair to use;
someone else's edited compilation is not, and nothing here is scraped
from a commercial joke site. See the header of `TrinketsJokes.kt`.

Excerpts is different again: each entry is a short (under 15 words)
attributed quote from a real work, rather than original writing. Most are
public domain. A smaller set of twentieth and twenty-first century
entries appear as brief attributed quotations, kept short deliberately.
Authors are also selected for their own record, not just for a good turn
of phrase, see the header of `TrinketsExcerpts.kt`. Two hard rules apply
here that don't apply to the other buckets:

1. **Every quote stays under 15 words.** This is a strict, non-negotiable
   ceiling regardless of the source's copyright status.
2. **Each author/work pair is used at most once across the entire bucket.**
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
question entirely. A single attributed line from a poem is allowed (see
entries 304+), but never more than one line, and never enough to stand in
for reading the poem itself; a public-domain poem reproduced in full
belongs in the Poems bucket instead.

**A note on accuracy:** entries were checked individually
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
   # from tool/src/main/kotlin/com/tyshi00/trinkets
   grep -oP '(?<=Joke\(\d, ")[^"]+' TrinketsJokes.kt | sort | uniq -d
   ```
4. For **Today in History**, only add facts you can verify, no invented or
   approximated events.
5. For **Excerpts**, verify the quote's exact wording and real source
   before adding it (a web search against the actual text, not just a
   quote-aggregator site), confirm it's under 15 words, confirm the
   specific work hasn't already supplied an entry, and confirm the English
   translation used (if any) is itself old enough to be public domain.
6. For **Poems** 11+, confirm the text is public domain in the US, the
   author has no record of bigotry, and the wording matches a reliable
   source. Set `author`; leave it null only for originals.
7. Rebuild. No schema or code changes are needed to add more entries to
   an existing bucket, only more list items.
