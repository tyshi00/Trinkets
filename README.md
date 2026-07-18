# Trinkets

A small daily companion for the Light Phone III. A countdown to what you're looking forward to, and a handful of quiet, distraction-free daily reads to start the day with.

This repo is built on Light's SDK scaffolding for Light Phone III tools, but at this point it's really just the Trinkets app. The actual app code lives in [`tool/`](./tool), see [`tool/README.md`](./tool/README.md) for the same rundown below, plus anything app-specific. SDK-level documentation (for anyone building their own separate tool on this scaffolding) is still in [`docs/`](./docs).

## What it does

- **Countdowns**: track up to 3 countdowns to whatever you're looking forward to, a trip, a birthday, an anniversary, a milestone. Each one has a name, a date (past or future, so it works for "days since" too), and optional notes. Add or delete them from the calendar icon on the Home screen's bottom bar.
- **Poem of the Day**: a short original poem, one per day, 360 in rotation
- **Literary Excerpt**: a short, verified, attributed quote from a real public-domain literary work, one per day
- **Today in History**: real historical events that happened on today's date, deliberately sourced from across Africa, Asia, the Americas, the Middle East, and Oceania rather than any one region
- **Philosophy Prompt**: a daily thought experiment with a follow-up question, meant to sit with for a minute
- **Morning Prompt**: a motivational line, ranging from gentle to steady to energizing, with a Settings option to lock in your preferred intensity
- **Joke of the Day**: a wholesome, dad-joke-style setup and punchline (tap to reveal)
- **Trivia**: a short, off-beat Q&A fact (tap to reveal the answer)
- **Settings**: turn any of the seven daily features on or off, choose what the Home screen shows by default (Countdowns or any enabled feature), set your preferred Morning Prompt intensity, invert the screen color, or reset all data

## Navigation

The bottom bar is consistent everywhere it appears on Home:
- **Left (calendar icon)**: Countdowns, view, add, or delete
- **Middle (list icon)**: Features, a directory of every enabled daily-content feature
- **Right (settings icon)**: Settings

## Using it

Install the APK on your Light Phone III, or side-load it onto an Android emulator running the [LightOS Emulator](sdk/emulator) for testing (see [docs/system_app](docs/system_app)).

## Content

Poems, excerpts, jokes, trivia, philosophy prompts, morning prompts, and Today in History facts are all bundled directly into the app, not fetched from the network, everything works offline. They rotate day to day with no repeats until each bucket has fully cycled through. Six of the seven buckets hold 360 items each (a full year of daily rotation with no repeats). The Literary Excerpt bucket is smaller (131 items) by design, since each entry there is a real, verified quote from a distinct public-domain work rather than original writing, see [`tool/docs/CONTENT.md`](tool/docs/CONTENT.md) for the full explanation and how to keep growing each bucket over time.

## [Complete SDK Documentation](./docs)
