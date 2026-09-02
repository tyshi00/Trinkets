# Trinkets

A small daily companion for the Light Phone III. A countdown to what you're looking forward to, and a handful of quiet, distraction-free daily reads to start the day with.

This repo is built on Light's SDK scaffolding for Light Phone III tools, but at this point it's really just the Trinkets app. The actual app code lives in [`tool/`](./tool), see [`tool/README.md`](./tool/README.md) for the same rundown below, plus anything app-specific. SDK-level documentation (for anyone building their own separate tool on this scaffolding) is still in [`docs/`](./docs).

## What it does

- **Countdowns**: track up to 7 countdowns to whatever you're looking forward to, a trip, a birthday, an anniversary, a milestone. Each one has a name, a date (past or future, so it works for "days since" too), and optional notes. Add or delete them from the calendar icon on the Home screen's bottom bar. Star one to pin it to the Home screen; leave them all unstarred and Home shows whichever is soonest.
- **Poem of the Day**: a short original poem, one per day, 360 in rotation
- **Literary Excerpt**: a short, verified, attributed quote from a real public-domain literary work, one per day
- **Today in History**: real historical events that happened on today's date, deliberately sourced from across Africa, Asia, the Americas, the Middle East, and Oceania rather than any one region
- **Reflection**: an open journaling prompt to sit with. Three are offered each day; tap or shake the phone to move between them, and check one off with the circle button once you've used it so it drops out of the rotation until the rest of the pool has cycled through
- **Philosophy Prompt**: a daily thought experiment with a follow-up question, meant to sit with for a minute
- **Morning Prompt**: a motivational line, ranging from gentle to steady to energizing, with a Settings option to lock in your preferred intensity
- **Joke of the Day**: a wholesome, dad-joke-style setup and punchline (tap to reveal). One-liners without a natural setup borrow one of a few rotating openers instead
- **Trivia**: a short, off-beat Q&A fact (tap to reveal the answer)
- **Split home screen**: an optional Settings toggle that shows two features at once, stacked, with a divider between them. Pick which goes on top and which underneath; either slot can hold Countdowns
- **Settings**: turn any of the eight daily features on or off, choose what the Home screen shows by default (Countdowns or any enabled feature), enable the split home screen and pick its two features, set your preferred Morning Prompt intensity, choose date and time formats, invert the screen color, or reset all data

## Navigation

The bottom bar is consistent everywhere it appears on Home:
- **Left (calendar icon)**: Countdowns, view, add, or delete
- **Middle (list icon)**: Features, a directory of every enabled daily-content feature
- **Right (settings icon)**: Settings

## Using it

Install the APK on your Light Phone III, or side-load it onto an Android emulator running the [LightOS Emulator](sdk/emulator) for testing (see [docs/system_app](docs/system_app)).

## Content

All content is bundled directly into the app, not fetched from the network, so everything works offline. Each bucket rotates day to day and cycles fully before repeating.

| Bucket | Items |
| --- | --- |
| Poems | 133 |
| Philosophy prompts | 360 |
| Morning prompts | 360 |
| Trivia | 360 |
| Today in History | 582 |
| Literary excerpts | 328 |
| Jokes | 207 |
| Reflection prompts | 94 |

The buckets aren't the same size on purpose. Philosophy, morning prompts, and trivia are original writing, so they hit a full 360-day year. Today in History is larger because it's keyed to real calendar dates rather than a rotation. Poems, excerpts, jokes, and reflection prompts are smaller because each entry has to be a real, attributable thing rather than something invented to fill a slot; Poems pairs fourteen originals with public-domain poems that are found and verified one at a time. See [`tool/docs/CONTENT.md`](tool/docs/CONTENT.md) for sourcing rules and how to keep growing each bucket.

## Credits and License

Trinkets is built on the [Light SDK](https://github.com/lightphone/light-sdk) by The Light Phone, which is MIT licensed. The MIT License and copyright notice are retained in [`LICENSE`](./LICENSE).

Poem of the Day reproduces public-domain poems in full, each credited on screen to its author (Frost, Hughes, Dickinson, Rossetti, Dunbar, Teasdale, Millay, and others). Literary Excerpts quotes single lines (under 15 words) from real works, including some still in copyright, as brief attributed quotations.

The Reflection prompts are adapted from [Reflect](https://github.com/Zarrasko/reflect) by Zarrasko, a community tool for the Light Phone III. Trinkets uses the prompt text but drops Reflect's nine-category filtering, drawing from the whole pool instead, and adds the check-off system. The shake-to-shuffle gesture is also adapted from Reflect's `LightShakeDetector`, reworked to fit the SDK version vendored here.

## [Complete SDK Documentation](./docs)
