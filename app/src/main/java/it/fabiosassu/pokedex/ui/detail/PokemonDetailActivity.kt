package it.fabiosassu.pokedex.ui.detail

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import it.fabiosassu.pokedex.R
import it.fabiosassu.pokedex.base.BaseActivity
import kotlinx.android.synthetic.main.activity_pokemon_detail.*

/**
 * An activity representing a single Pokemon detail screen.
 */
class PokemonDetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_detail)
        setSupportActionBar(detail_toolbar)

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            val navHostFragment =
                supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
            val navController = navHostFragment.navController
            navController.setGraph(R.navigation.detail_nav_graph, intent?.extras)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
        when (item.itemId) {
            android.R.id.home -> {
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }

}
