package it.fabiosassu.pokedex.ui.splash

import android.content.Intent
import android.os.Bundle
import it.fabiosassu.pokedex.base.BaseActivity
import it.fabiosassu.pokedex.ui.list.PokemonListActivity

class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this@SplashActivity, PokemonListActivity::class.java))
        finish()
    }
}