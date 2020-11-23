package it.fabiosassu.pokedex.ui.detail


import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.util.Pair
import androidx.core.view.isVisible
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.snackbar.Snackbar
import io.uniflow.androidx.flow.onEvents
import io.uniflow.androidx.flow.onStates
import io.uniflow.core.flow.data.UIEvent
import io.uniflow.core.flow.data.UIState
import it.fabiosassu.pokedex.R
import it.fabiosassu.pokedex.base.BaseFragment
import it.fabiosassu.pokedex.model.PokemonDetailResponse
import it.fabiosassu.pokedex.model.Sprites
import it.fabiosassu.pokedex.ui.detail.adapter.PokemonSkillsAdapter
import it.fabiosassu.pokedex.util.GlideApp
import it.fabiosassu.pokedex.util.toCommaSeparatedString
import kotlinx.android.synthetic.main.activity_pokemon_detail.*
import kotlinx.android.synthetic.main.fragment_pokemon_detail.*
import kotlinx.android.synthetic.main.layout_carousel.*
import kotlinx.android.synthetic.main.layout_empty_view.*
import kotlinx.android.synthetic.main.layout_loading_view.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*
import kotlin.math.abs

/**
 * this is the fragment that will display the pokemon detail card.
 *
 */
class PokemonDetailFragment : BaseFragment() {

    private var pokemonNumber: Int? = null
    private val mViewModel: PokemonDetailViewModel by viewModel()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        arguments?.let { pokemonNumber = PokemonDetailFragmentArgs.fromBundle(it).pokemonId }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return LayoutInflater.from(requireContext())
            .inflate(R.layout.fragment_pokemon_detail, container, false)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observe incoming states
        onStates(mViewModel) { state ->
            when (state) {
                is PokemonDetailState -> {
                    onPokemonDetailsRetrieved(state.pokemon)
                }
                is UIState.Empty -> {
                    activity?.progress_bar?.isVisible = false
                    activity?.emptyView?.isVisible = true
                }
            }
        }

        onEvents(mViewModel) {
            when (val event = it.take()) {
                is UIEvent.Loading -> activity?.progress_bar?.isVisible = true
                is UIEvent.Error -> {
                    activity?.progress_bar?.isVisible = false
                    event.error?.message?.let { msg ->
                        Snackbar.make(
                            root_detail_view,
                            msg,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        // get pokemon detail
        pokemonNumber?.let { mViewModel.getPokemonDetail(it) }
    }

    private fun onPokemonDetailsRetrieved(pokemon: PokemonDetailResponse?) {
        // setup toolbar
        activity?.toolbar_layout?.title = pokemon?.name?.capitalize(Locale.getDefault())
        // hide empty view because we have data
        activity?.emptyView?.isVisible = false
        activity?.progress_bar?.isVisible = false

        // in order to fade the carousel when the toolbar is collapsed
        activity?.app_bar?.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            activity?.carouselView?.alpha =
                1.0f - abs(verticalOffset / appBarLayout.totalScrollRange.toFloat())
        })
        setupCarousel(pokemon?.sprites)
        type_value?.text = pokemon?.types?.map { type -> type.type?.name }
            ?.toCommaSeparatedString(capitalizedWords = true)
        pokemon?.stats?.map { it.stat?.name }
        // set the skills adapter
        skills_recycler?.adapter = PokemonSkillsAdapter(pokemon?.stats?.map {
            Pair(
                it.stat?.name?.replace('-', ' ')?.capitalize(Locale.getDefault()), it.baseStat
            )
        }?.toMutableList())
    }

    private fun setupCarousel(sprites: Sprites?) {
        val spriteUrls = listOfNotNull(
            sprites?.frontDefault,
            sprites?.backDefault,
            sprites?.frontFemale,
            sprites?.backFemale,
            sprites?.frontShiny,
            sprites?.backShiny,
            sprites?.frontShinyFemale,
            sprites?.backShinyFemale,
        )
        activity?.carouselView?.setImageListener { position, imageView ->
            GlideApp.with(imageView.context)
                .load(Uri.parse(spriteUrls[position]))
                .centerCrop()
                .into(imageView)
        }
        activity?.carouselView?.pageCount = spriteUrls.size
    }

    companion object {
        /**
         * The fragment argument representing the pokemon ID that this fragment
         * represents.
         */
        const val ARG_POKEMON_ID = "pokemon_id"
    }
}
