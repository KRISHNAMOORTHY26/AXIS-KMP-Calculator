# AXIS-KMP-Calculator


A cross-platform mobile project built using **Kotlin Multiplatform (KMP)** that shares business logic across **Android** and **iOS**, while keeping separate UI layers.

## ⚙️ Setup Instructions

Follow these steps to clone, open, and run the project successfully

### 1. Clone the Repository
Open your terminal and run:
---bash
git clone

2. Open the Project
-------------------------
Open the project in Android Studio (Ladybug or newer).

Make sure you have the Kotlin Multiplatform plugin enabled.

3. Configure Android Environment
--------------------------------
Use JDK 17 or above.

Set up the Android SDK path properly.

Sync Gradle to download all required dependencies.

4. Configure iOS Environment (optional)

To build and run the iOS target:

Install Xcode (latest stable version).

Open the iosApp/ folder in Xcode.

Ensure the iOS target is linked with the shared framework.

 Build Instructions
 --------------------
🔹 Build Android App
./gradlew :androidApp:assembleDebug

🔹 Build Shared Module (.aar)
./gradlew :shared:assembleRelease

Generated AAR file path:
shared/build/outputs/aar/shared-release.aar
