package it.fabiosassu.pokedex.ui.detail


import io.uniflow.core.flow.data.UIState
import it.fabiosassu.pokedex.model.PokemonDetailResponse


data class PokemonDetailState(val pokemon: PokemonDetailResponse? = null) : UIState()