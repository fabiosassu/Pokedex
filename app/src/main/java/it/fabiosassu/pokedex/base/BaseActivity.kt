package it.fabiosassu.pokedex.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import it.fabiosassu.pokedex.R

open class BaseActivity : AppCompatActivity() {

    fun isTablet(): Boolean {
        return resources.getBoolean(R.bool.is_tablet)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        try {
            requestedOrientation =
                if (isTablet()) ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE else ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}