package it.fabiosassu.pokedex.ui.detail

import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.data.UIEvent
import io.uniflow.core.flow.data.UIState
import it.fabiosassu.pokedex.repository.PokemonRepo


class PokemonDetailViewModel(private val pokemonRepo: PokemonRepo) :
    AndroidDataFlow(defaultState = UIState.Empty) {

    init {
        action { sendEvent(UIEvent.Loading) }
    }

    fun getPokemonDetail(pokemonNo: Int) = action {
        setStateAsync {
            try {
                val pokemon = pokemonRepo.getPokemonDetails(pokemonNo)
                PokemonDetailState(pokemon)
            } catch (e: Exception) {
                sendEvent(UIEvent.Error(error = e, state = UIState.Empty))
                UIState.Empty
            }
        }
    }

}