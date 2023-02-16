# Pokedex

A list of all the Pokemons on your hand.

[![Licence Badge](https://img.shields.io/badge/License-Apache%202.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)
[![Medium Badge](https://img.shields.io/badge/Medium-12100E?style=for-the-badge&logo=medium&logoColor=white)](https://medium.com/pink-room-club)
[![Twitter Badge](https://img.shields.io/badge/Twitter-1DA1F2?style=for-the-badge&logo=twitter&logoColor=white)](https://twitter.com/pinkroomdev)
[![Instagram Badge](https://img.shields.io/badge/Instagram-E4405F?style=for-the-badge&logo=instagram&logoColor=white)](https://www.instagram.com/pinkroom.dev/)

I’ve started this project to explore the current state of Kotlin Multiplatform Mobile - KMM and its tech stack. The main goal is to see how much code I can share when building Android and iOS native UIs with a backbone written in Kotlin.

## Setup (for Android Studio)

1. [Download and install](https://developer.android.com/studio) Android Studio
1. Clone this repository
1. Run the project

## Tech stack
### Android
For the Android UI, we decided to use [Jetpack Compose](https://developer.android.com/jetpack/compose).

### iOS
For the iOS UI we decided to use [SwiftUI](https://developer.apple.com/xcode/swiftui/).

### Shared code
For the shared code the following are used: 
- [KMM ViewModels](https://github.com/rickclephas/KMM-ViewModel) for sharing viewmodels between platforms;
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) for asynchronous tasks;
- [SQLDelight](https://cashapp.github.io/sqldelight/2.0.0-alpha05/multiplatform_sqlite/) for database operations;
- [Koin](https://insert-koin.io/) for dependency injection; 
- [KtorFit](https://github.com/Foso/Ktorfit) for making internet requests;
- [Multiplatform Paging](https://github.com/kuuuurt/multiplatform-paging) for pagination.

<img src="https://user-images.githubusercontent.com/24237865/83422649-d1b1d980-a464-11ea-8c91-a24fdf89cd6b.png" align="right" width="15%"/>

## Open API

Pokedex using the [PokeAPI](https://pokeapi.co/) for constructing RESTful API.<br>
PokeAPI provides a RESTful API interface to highly detailed objects built from thousands of lines of data related to Pokémon.

## Additional resources 
- [Kotlin Multiplatform](https://kotlinlang.org/docs/reference/multiplatform.html) 
- [Kotlin Multiplatform Mobile](https://kotlinlang.org/docs/reference/mobile/kotlin-multiplatform-mobile.html)

## License

    Copyright 2023 Pink Room, Lda

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.