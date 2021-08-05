# ShortlyApp

![resize-1628162762777731559Screenshot1628162130](https://user-images.githubusercontent.com/26321700/128342422-beb5b312-fded-4dcb-b4e2-ffe40de7f680.png) ![resize-1628162830805269836Screenshot1628162215](https://user-images.githubusercontent.com/26321700/128342562-0b02df2b-3fb2-46fc-ac36-db63b2fb535a.png)

## Architecture

- Single Activity
- MVVM Pattern

**View:** Renders UI and delegates user actions to ViewModel

**ViewModel:** Can have simple UI logic but most of the time just gets the data from UseCase

**UseCase:** Contains all business rules and they written in the manner of single responsibility principle

**Repository:** Single source of data. Responsible to get data from local or remote data source

## Dependencies

- **[Navigation Component](https://developer.android.com/jetpack/androidx/releases/navigation):** Consistent navigation between views
- **[LiveData](https://developer.android.com/topic/libraries/architecture/livedata):** Lifecycle aware observable and data holder
- **[ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel):** Holds UI data across configuration changes
- **[Databinding](https://developer.android.com/topic/libraries/data-binding/):** Binds UI components in layouts to data sources
- **[Coroutines](https://github.com/Kotlin/kotlinx.coroutines):** Asynchronous programming
- **[Room](https://developer.android.com/topic/libraries/architecture/room):** Object mapping for SQLite
- **[Hilt](https://github.com/googlecodelabs/android-hilt):** Dependency injector
