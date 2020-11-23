package it.fabiosassu.pokedex.base

import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    fun isTablet(): Boolean = (activity as? BaseActivity)?.isTablet() == true

}