# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Build Commands

```bash
# Build debug APK
./gradlew assembleDebug

# Build release APK
./gradlew assembleRelease

# Install on connected device/emulator
./gradlew installDebug

# Run unit tests
./gradlew test

# Run instrumentation tests
./gradlew connectedAndroidTest

# Run lint
./gradlew lint
```

**JDK Requirement**: JDK 17 is required. JDK 25+ causes kapt compatibility issues. Set via Android Studio: File → Settings → Build → Gradle → Gradle JDK = 17, or set `JAVA_HOME` to a JDK 17 path.

## Architecture

This is a maternal and child health tracking app (MVVM + Repository pattern) supporting English and French. It covers: health articles, vaccination tracking, child growth monitoring (WHO standards), pregnancy tracking with prenatal visits, symptom prediction, and clinic finding.

### Layer Structure

```
UI Layer        → Activities + Fragments (Material Design, bottom nav with 5 tabs)
ViewModel Layer → StateFlow + Kotlin Coroutines Flow for reactive state
Repository Layer→ Clean abstraction over DAOs (suspend functions + Flow streams)
Data Layer      → Room Database (7 tables) + SharedPreferences
```

### Package Layout (`com.example.helloapp/`)

| Package | Purpose |
|---|---|
| `data/` | Room entities, DAOs, AppDatabase singleton |
| `repository/` | One repository per domain (Article, Vaccination, Growth, Pregnancy, Clinic) |
| `viewmodel/` | One ViewModel per domain; `ArticleViewModel` is the most complex (~490 lines) |
| `fragment/` | Fragments hosted in MainActivity |
| `adapter/` | RecyclerView adapters |
| `service/` | `ArticleFetcher` — returns hardcoded articles in EN/FR (no remote API) |
| `worker/` | `HealthReminderWorker` for daily WorkManager reminders |
| `util/` | `LanguageHelper`, `NotificationHelper`, `WHOGrowthStandards`, `SearchSuggestions` |

### Navigation

**Bottom navigation** in `MainActivity` drives 5 tabs: Articles, Clinics, Vaccination+Growth, Pregnancy, Symptom Predictor.

Detail screens are separate Activities: `ArticleDetailActivity`, `FavoritesActivity`, `GrowthTrackerActivity`, `VaccinationActivity`, `PregnancyTrackerActivity`, `RapidTestTimerActivity`.

### Key Architectural Details

- **Room Database**: `AppDatabase` is a thread-safe singleton (volatile + synchronized). Schema has 7 tables. `fallbackToDestructiveMigration()` is enabled — schema changes will wipe data in dev builds.
- **All DAOs return `Flow<T>`** for live updates. ViewModels expose `StateFlow` to the UI.
- **Articles are hardcoded** in `ArticleFetcher.kt` (~38 health conditions in EN/FR). OkHttp is a dependency but not actively used for fetching.
- **`ArticleViewModel`** contains a sophisticated symptom matching engine: maps informal terms (e.g. "belly" → "abdominal") to medical conditions with HIGH/MODERATE/LOW confidence scoring. Also handles fuzzy search with Levenshtein distance via `SearchSuggestions.kt`.
- **Language switching** (`LanguageHelper`) applies a locale at the `Activity.attachBaseContext()` level; all activities must call it.
- **Background tasks**: `HealthReminderWorker` (WorkManager) sends daily vaccination/prenatal reminders. `BootReceiver` re-schedules after device restart.
- **WHO growth standards** percentile calculations live in `WHOGrowthStandards.kt` and are used by `GrowthViewModel` for charting with MPAndroidChart.
- **All entities implement `Parcelable`** (via `@Parcelize`) for passing between Activities via Intents.
- **SharedPreferences** stores: language selection (`language_pref`) and onboarding completion status.

## Key Dependencies

- **Room 2.6.1** with kapt for annotation processing
- **Kotlin Coroutines 1.7.3** + lifecycle-viewmodel-ktx for Flow/StateFlow
- **MPAndroidChart v3.1.0** (JitPack) for growth charts
- **Google Play Services Maps + Location** for clinic finder
- **WorkManager 2.9.0** for background reminders
- **Jsoup 1.17.2** for HTML content parsing in articles
