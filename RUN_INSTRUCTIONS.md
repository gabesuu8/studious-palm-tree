# How to Run the App on Emulator

## Prerequisites
- Android SDK installed (usually at `~/Library/Android/sdk` on macOS)
- Android Emulator created and running
- Java/JDK installed

## Step-by-Step Command Line Instructions

### 1. Start the Android Emulator

First, list available emulators:
```bash
emulator -list-avds
```

Start an emulator (replace `YourEmulatorName` with actual name):
```bash
emulator -avd YourEmulatorName &
```

Or start it from Android Studio: Tools → Device Manager → Click play button

### 2. Wait for Emulator to Boot

Wait until the emulator fully boots (you'll see the Android home screen).

### 3. Build and Install the App

From the project root directory (`/Users/gabrielsu/studious-palm-tree`):

```bash
# Build the debug APK
./gradlew assembleDebug

# Install on the running emulator
./gradlew installDebug
```

### 4. Launch the App

```bash
adb shell am start -n com.example.helloapp/.MainActivity
```

Or simply open the app from the emulator's app drawer (look for "HelloApp").

## Alternative: All-in-One Command

You can also build, install, and run in one go:
```bash
./gradlew installDebug && adb shell am start -n com.example.helloapp/.MainActivity
```

## Troubleshooting

### If `adb` command not found:
Add Android SDK platform-tools to your PATH:
```bash
export PATH=$PATH:~/Library/Android/sdk/platform-tools
```

### If emulator not found:
Add Android SDK emulator to your PATH:
```bash
export PATH=$PATH:~/Library/Android/sdk/emulator
```

### Check if emulator is connected:
```bash
adb devices
```
You should see your emulator listed.
