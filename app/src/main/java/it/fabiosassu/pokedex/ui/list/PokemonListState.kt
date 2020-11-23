package it.fabiosassu.pokedex.ui.list


import androidx.paging.PagingData
import io.uniflow.core.flow.data.UIEvent
import io.uniflow.core.flow.data.UIState
import it.fabiosassu.pokedex.model.Pokemon
import kotlinx.coroutines.flow.Flow


data class PokemonListState(
    val pokemonList: Flow<PagingData<Pokemon>>? = null,
) : UIState()

sealed class PokemonListEvent : UIEvent() {
    class OpenDetail(val pokemonId: Int?) : PokemonListEvent()
}