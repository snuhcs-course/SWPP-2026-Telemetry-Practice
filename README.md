# Week 11 — Firebase Analytics: Button Telemetry

**SWPP 2026 Fall · Android (Kotlin)**

This tiny counter app generates a custom `button_tap` event on every **Count** or **Reset** click, with a fixed `button_id` parameter. The UI also runs without Firebase so you can test the buttons before connecting your own project.

## 1. Run the app

Open this folder in Android Studio. Use JDK 21, Android SDK 36, an Android emulator or device with Google Play services, and Gradle 8.13. Click Run, then press Count three times and Reset once. The number should go `0 → 1 → 2 → 3 → 0`.

From the terminal, you can build and run the UI test with:

```bash
./gradlew assembleDebug
./gradlew connectedDebugAndroidTest   # needs a running emulator/device
```

## 2. Connect Firebase Analytics

1. Open [Firebase Console](https://console.firebase.google.com/) and create a project **with Google Analytics enabled**.
2. Add an **Android app** using the exact application ID **`com.example.telemetrypractice`** (also in `app/build.gradle.kts`).
3. Download **`google-services.json`** into **`app/google-services.json`**. Sync Gradle and run the app again; the status message should say **Firebase Analytics connected**.
4. Never commit `google-services.json`. This repository ignores the file. Each student/group connects their own Firebase project.

The Google Services Gradle plugin is applied automatically when the file is present. The project includes the Firebase Analytics SDK and Firebase BoM. In `MainActivity.kt`, inspect the `record(buttonId)` helper and both button click listeners. The event name is `button_tap`; the parameter is `button_id` (`count` or `reset`). For practice, try changing one button ID and confirm it appears in DebugView.

## 3. Verify in Firebase DebugView

Enable debug events in your emulator/device using the **application ID**:

```bash
adb shell setprop debug.firebase.analytics.app com.example.telemetrypractice
```

Open **Firebase Console → Analytics → DebugView**, select your debug device, tap **Count × 3**, then **Reset × 1**. Expect **four** `button_tap` events; expanding an event shows `button_id=count` or `button_id=reset`. DebugView may take a short time to populate. If events do not appear, check that the project enables Analytics, the package ID matches, the correct emulator is selected, and that Google Play services is available. Optional diagnostic command: `adb logcat -s FA FA-SVC`.

Turn off debugging afterwards:

```bash
adb shell setprop debug.firebase.analytics.app .none.
```

## 4. Evidence to submit

Save **`telemetry_app.png`** (counter app screen) and **`telemetry_debugview.png`** (DebugView event detail with the `button_id` parameter). Use fixed button IDs only; do not record names, emails, user-entered text, or other personal data.

Firebase docs: [Android setup](https://firebase.google.com/docs/android/setup), [log events](https://firebase.google.com/docs/analytics/android/events), [DebugView](https://firebase.google.com/docs/analytics/debugview).
