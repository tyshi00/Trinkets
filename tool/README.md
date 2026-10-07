# Trinkets

A small daily companion for the Light Phone III. Countdowns to what you're looking forward to, plus a handful of quiet daily reads to start the day with.

This is the app module. See the [main README](../README.md) for the full repo overview.

## Features

- **Countdowns**: track up to 7 countdowns to a trip, a birthday, an anniversary, or a milestone. Each has a name, a date (past or future, so it works for "days since" too), and optional notes. Star one to pin it to Home; with none starred, Home shows the soonest.
- **Poem of the Day**: one poem per day, original or public-domain.
- **Literary Excerpt**: a short, verified, attributed quote from a real literary work, one per day.
- **Today in History**: real events from today's date, sourced from across Africa, Asia, the Americas, the Middle East, and Oceania rather than any one region.
- **Reflection**: an open journaling prompt. Three are offered each day; tap or shake the phone to move between them. Check one off with the circle button once used, and it leaves the rotation until the rest of the pool has cycled through.
- **Philosophy Prompt**: a daily thought experiment with a follow-up question.
- **Morning Prompt**: a motivational line, from gentle to steady to energizing, with a Settings option to lock in your preferred intensity.
- **Joke of the Day**: a wholesome setup and punchline (tap to reveal). One-liners without a natural setup use one of a few rotating openers.
- **Trivia**: a short, off-beat Q&A fact (tap to reveal the answer).
- **Split home screen**: an optional Settings toggle that shows two features stacked, with a divider. Pick which goes on top and which underneath; either slot can hold Countdowns.
- **Settings**: turn any of the eight daily features on or off, choose what Home shows by default (Countdowns or any enabled feature), set up the split home screen, set your Morning Prompt intensity, choose date and time formats, invert the screen color, or reset all data.

## Usage

The bottom bar on Home:

- **Left (settings icon)**: Settings
- **Middle (list icon)**: Features, a directory of every enabled daily feature
- **Right (calendar icon)**: Countdowns, to view, add, or delete them

## Installation

See [Installation](../README.md#installation) in the main README.

## Content

All content is bundled into the app, so everything works offline. Each bucket rotates daily and cycles fully before repeating.

| Bucket | Items |
| --- | --- |
| Poems | 127 |
| Philosophy prompts | 360 |
| Morning prompts | 360 |
| Trivia | 360 |
| Today in History | 582 |
| Literary excerpts | 328 |
| Jokes | 207 |
| Reflection prompts | 94 |

The bucket sizes differ on purpose. Philosophy, morning prompts, and trivia are original writing, so they fill a 360-day year. Today in History is larger because it's keyed to calendar dates. Poems, excerpts, jokes, and reflection prompts are smaller because each entry must be a real, attributable item; Poems pairs fourteen originals with 113 public-domain poems found and verified one at a time. See [`docs/CONTENT.md`](docs/CONTENT.md) for sourcing rules and how to grow each bucket.

## Credits

Built on the [Light SDK](https://github.com/lightphone/light-sdk) by The Light Phone (MIT). The original copyright notice is kept in [LICENSE](../LICENSE), and the SDK's own README is kept in [README.light-sdk.md](../README.light-sdk.md).

The Reflection prompts are adapted from Reflect, an example tool from the Light SDK (MIT), via [Zarrasko's standalone extraction](https://github.com/Zarrasko/reflect). Used with permission. Trinkets uses the prompt text but drops Reflect's nine-category filtering, drawing from the whole pool instead, and adds the check-off system. The shake-to-shuffle gesture uses `LightShakeDetector` and `LightHapticFeedback`, ported from a newer Light SDK release.

Poem of the Day reproduces public-domain poems in full, each credited on screen to its author (Frost, Hughes, Dickinson, Rossetti, Dunbar, Teasdale, Millay, and others). Literary Excerpts quotes single lines (under 15 words) from real works, including some still in copyright, as brief attributed quotations.

## License

MIT. See [LICENSE](../LICENSE).

## Disclaimer

Unofficial, independent open-source project — not affiliated with or endorsed by The Light Phone, Inc. Light Phone and Light OS are trademarks of The Light Phone, Inc.
