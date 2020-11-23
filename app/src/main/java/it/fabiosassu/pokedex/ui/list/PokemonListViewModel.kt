package it.fabiosassu.pokedex.ui.list

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import io.uniflow.androidx.flow.AndroidDataFlow
import io.uniflow.core.flow.actionOn
import io.uniflow.core.flow.data.UIEvent
import io.uniflow.core.flow.data.UIState
import it.fabiosassu.pokedex.repository.PokemonRepo


class PokemonListViewModel(private val pokemonRepo: PokemonRepo) :
    AndroidDataFlow(defaultState = UIState.Empty) {

    init {
        action { sendEvent(UIEvent.Loading) }
    }

    fun getPokemonList() = action {
        // call to get data
        setStateAsync {
            val pokemon = pokemonRepo.getPokemonList(limit = 30, pokemonName = null, offset = null)
                .cachedIn(viewModelScope)
            PokemonListState(pokemonList = pokemon)
        }
    }

    fun openPokemonDetail(pokemonId: Int?) = actionOn<PokemonListState> {
        sendEvent(PokemonListEvent.OpenDetail(pokemonId))
    }

}