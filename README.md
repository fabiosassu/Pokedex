# Pokedex
A simple app displaying all pokemons into a recyclerview

- This app is written in Kotlin.
- Uses Clean Architecture (Repository pattern) with Uniflow lib.
- Use JetPack: (ViewModel, Room, Navigation)
- Uses Koin for dependencty injection.
- Uses Retrofit2 and OkHttp3 as network libraries.
- Uses Coroutines + Flow
- Uses Moshi and Glide.
- Works both online and offline.
- Has a reload button as an extra feature.

The app will display the pokemon list as paged. Whenever pokemons are fetched, a loader will be displayed
on the bottom side.