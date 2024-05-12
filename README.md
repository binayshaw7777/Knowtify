# Knowtify - A Compose Multiplatform Dictionary App

This project is a powerful and versatile dictionary app built using Compose, Ktor, and Room. It allows you to search for word definitions seamlessly across desktops (Windows, macOS), Android, and iOS.

## Key Features:
- Multiplatform Support: Enjoy a consistent dictionary experience on various devices, including desktops, Android, and iOS.
- Free Dictionary API Integration: Access comprehensive definitions from a reputable API.
- Ktor for Network Requests: Make efficient and reliable API calls using Ktor's modern networking library.
- Room Database for Offline Storage: Save search history and definitions locally for offline access.
- Beautiful Compose UI: Create an intuitive and visually appealing user interface with Compose.

## Libraries Used:
- [Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)
- [KTOR](https://ktor.io/)
- [Koin](https://insert-koin.io/)
- [Room Database](https://developer.android.com/kotlin/multiplatform/room)
- [SQLite](https://developer.android.com/kotlin/multiplatform/sqlite)
- [Navigation](https://www.jetbrains.com/help/kotlin-multiplatform-dev/compose-navigation-routing.html)
- [ViewModel](https://www.jetbrains.com/help/kotlin-multiplatform-dev/whats-new-compose-eap.html#lifecycle-library)
- [KotlinX Serialization](https://kotlinlang.org/docs/serialization.html)
- [Coroutines](https://discuss.kotlinlang.org/t/coroutines-with-multiplatform-projects/18006)

## Architecture:
![image](https://github.com/binayshaw7777/Knowtify/assets/62587060/119262d3-9677-42b4-b249-761fb5bac044)


## Getting Started:

Prerequisites:
- Android Studio with Compose plugins installed.
- Basic knowledge of Kotlin, Compose, Ktor, AndroidX libs like and Room, Navigation and etc.

  
## Clone the Project:
```Bash
git clone https://github.com/your-username/your-project-name.git
```

- Running the App:
Open the project in your preferred IDE.
Build and run the project for your target platform (Android, Desktop, or iOS) following the specific instructions for your IDE and platform.

## Understanding the Code Structure:
- <b>commonMain</b>: Contains platform-agnostic code, including data models, network logic, and core business logic.
- <b>androidMain</b>: Specific code for the Android platform, such as UI components using Compose.
- <b>desktopMain</b>: Specific code for desktop platforms (Windows and macOS), likely using Compose for desktop as well.
- <b>iosMain</b>: Specific code for the iOS platform, potentially using Compose for iOS (still under development).

## Technologies Used:
- Compose: A cutting-edge UI framework for building modern, declarative UIs.
- Ktor: A lightweight, asynchronous networking library for making API calls.
- Room: An SQLite wrapper for local data persistence with an intuitive API.
- Kotlin: A concise and expressive language for developing Android and other cross-platform applications.
