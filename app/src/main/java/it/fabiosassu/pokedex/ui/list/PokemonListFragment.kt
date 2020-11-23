package it.fabiosassu.pokedex.ui.list


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import io.uniflow.androidx.flow.onEvents
import io.uniflow.androidx.flow.onStates
import io.uniflow.core.flow.data.UIEvent
import it.fabiosassu.pokedex.R
import it.fabiosassu.pokedex.base.BaseFragment
import it.fabiosassu.pokedex.ui.detail.PokemonDetailActivity
import it.fabiosassu.pokedex.ui.detail.PokemonDetailFragment
import it.fabiosassu.pokedex.ui.list.adapter.PokemonAdapter
import it.fabiosassu.pokedex.ui.list.adapter.PostsLoadStateAdapter
import kotlinx.android.synthetic.main.fragment_pokemon_list.*
import kotlinx.android.synthetic.main.layout_empty_view.*
import kotlinx.android.synthetic.main.layout_loading_view.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * this is the fragment that will display the pokemon list.
 *
 */
class PokemonListFragment : BaseFragment() {

    private val mViewModel: PokemonListViewModel by viewModel()
    private val mAdapter = PokemonAdapter(object : (Int?) -> Unit {
        override fun invoke(pokemonId: Int?) {
            mViewModel.openPokemonDetail(pokemonId)
        }
    })
    val spanCount
        get() = if (isTablet()) 5 else 3

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return LayoutInflater.from(requireContext())
            .inflate(R.layout.fragment_pokemon_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view?.adapter = mAdapter

        val gridLayoutManager = GridLayoutManager(activity, spanCount)
        gridLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (mAdapter.getItemViewType(position)) {
                    PokemonAdapter.LOADING_VIEW_TYPE -> spanCount
                    PokemonAdapter.POKEMON_TYPE -> 1
                    else -> -1
                }
            }
        }
        recycler_view?.layoutManager = gridLayoutManager

        recycler_view?.adapter = mAdapter.withLoadStateHeaderAndFooter(
            header = PostsLoadStateAdapter(mAdapter),
            footer = PostsLoadStateAdapter(mAdapter)
        )

        // Observe incoming states
        onStates(mViewModel) { state ->
            when (state) {
                is PokemonListState -> {
                    lifecycleScope.launch {
                        state.pokemonList?.collectLatest {
                            progress_bar.isVisible = false
                            mAdapter.submitData(it)
                            emptyView.isVisible = progress_bar.isVisible == false && mAdapter.itemCount == 0
                            recycler_view.isVisible = mAdapter.itemCount > 0
                        }
                    }
                }
            }
        }

        onEvents(mViewModel) {
            when (val event = it.take()) {
                is UIEvent.Loading -> progress_bar.isVisible = true
                is PokemonListEvent.OpenDetail -> {
                    val intent =
                        Intent(
                            this@PokemonListFragment.activity,
                            PokemonDetailActivity::class.java
                        ).apply {
                            putExtra(PokemonDetailFragment.ARG_POKEMON_ID, event.pokemonId)
                        }
                    this@PokemonListFragment.startActivity(intent)
                }
            }
        }

        // first load
        mViewModel.getPokemonList()
    }

}
