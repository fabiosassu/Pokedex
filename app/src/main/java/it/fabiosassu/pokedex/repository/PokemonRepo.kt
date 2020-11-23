package it.fabiosassu.pokedex.repository

import androidx.paging.PagingData
import it.fabiosassu.pokedex.model.Pokemon
import it.fabiosassu.pokedex.model.PokemonDetailResponse
import kotlinx.coroutines.flow.Flow


interface PokemonRepo {

    suspend fun getPokemonList(
        pokemonName: String?,
        limit: Int,
        offset: Int?
    ): Flow<PagingData<Pokemon>>

    suspend fun getPokemonDetails(
        pokemonNumber: Int,
    ): PokemonDetailResponse?
}