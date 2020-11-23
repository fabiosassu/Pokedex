package it.fabiosassu.pokedex

import android.app.Application
import it.fabiosassu.pokedex.di.apiModule
import it.fabiosassu.pokedex.di.netModule
import it.fabiosassu.pokedex.di.repoModule
import it.fabiosassu.pokedex.di.viewModelScope
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()
        configureKoin()
    }

    private fun configureKoin() {
        startKoin {
            androidContext(this@Application)
            modules(netModule, apiModule, viewModelScope, repoModule)
        }
    }
}