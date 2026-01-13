# Hello Android App

A simple Android application that displays "hello" on the home screen.

## Prerequisites

- Android Studio (latest version recommended)
- Android SDK (API level 24 or higher)
- Android Emulator or physical device

## Setup Instructions

1. **Open the project in Android Studio:**
   - Launch Android Studio
   - Select "Open an Existing Project"
   - Navigate to this directory and select it

2. **Sync Gradle:**
   - Android Studio should automatically sync Gradle files
   - If not, click "Sync Now" when prompted

3. **Set up Android Emulator:**
   - Open AVD Manager (Tools > Device Manager)
   - Create a new virtual device if you don't have one
   - Select a device (e.g., Pixel 5)
   - Choose a system image (API 24 or higher, recommended: API 34)
   - Finish the setup

4. **Run the app:**
   - Click the green "Run" button (or press Shift+F10)
   - Select your emulator from the device list
   - The app will build and launch on the emulator
   - You should see "hello" displayed in the center of the screen

## Project Structure

```
app/
├── src/
│   └── main/
│       ├── java/com/example/helloapp/
│       │   └── MainActivity.kt
│       ├── res/
│       │   ├── layout/
│       │   │   └── activity_main.xml
│       │   └── values/
│       │       ├── strings.xml
│       │       ├── colors.xml
│       │       └── themes.xml
│       └── AndroidManifest.xml
└── build.gradle
```

## Building from Command Line

If you prefer using the command line:

```bash
# Build the app
./gradlew assembleDebug

# Install on connected device/emulator
./gradlew installDebug

# Run the app
adb shell am start -n com.example.helloapp/.MainActivity
```

## Requirements

- Minimum SDK: 24 (Android 7.0)
- Target SDK: 34 (Android 14)
- Kotlin version: 1.9.0
- Gradle version: 8.0
