package it.fabiosassu.pokedex.di

import android.app.Application
import android.content.Context
import it.fabiosassu.pokedex.R
import it.fabiosassu.pokedex.api.PokemonApi
import it.fabiosassu.pokedex.database.PokemonDb
import it.fabiosassu.pokedex.repository.PokemonRepo
import it.fabiosassu.pokedex.repository.PokemonRepoImpl
import it.fabiosassu.pokedex.util.OkHttpClientUtils
import it.fabiosassu.pokedex.ui.detail.PokemonDetailViewModel
import it.fabiosassu.pokedex.ui.list.PokemonListViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val netModule = module {

    fun provideOkHttpClient(): OkHttpClient = OkHttpClientUtils.getOkHttpClient()

    fun provideRetrofit(context: Context, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(context.getString(R.string.base_url))
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
    }

    single { provideOkHttpClient() }
    single { provideRetrofit(androidContext(), get()) }
}

val viewModelScope = module {
    viewModel { PokemonListViewModel(get()) }
    viewModel { PokemonDetailViewModel(get()) }
}

val apiModule = module {
    fun providePokemonApi(retrofit: Retrofit): PokemonApi {
        return retrofit.create(PokemonApi::class.java)
    }

    single { providePokemonApi(get()) }
}

val repoModule = module {

    fun providePokemonDb(app: Application, useInMemoryDb: Boolean): PokemonDb {
        return PokemonDb.create(app, useInMemoryDb)
    }

    fun providePokemonRepo(
        pokemonApi: PokemonApi,
        pokemonDb: PokemonDb
    ): PokemonRepo {
        return PokemonRepoImpl(pokemonDb, pokemonApi)
    }

    single { providePokemonDb(androidApplication(), false) }
    single { providePokemonRepo(get(), get()) }
}
