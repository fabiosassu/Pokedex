package it.fabiosassu.pokedex.api

import it.fabiosassu.pokedex.model.PokemonDetailResponse
import it.fabiosassu.pokedex.model.PokemonListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {

    @GET("pokemon/{pokemonName}")
    suspend fun getPokemons(
        @Path("pokemonName") pokemonName: String? = "",
        @Query("limit") limit: Int? = null,
        @Query("offset") offset: Int? = null
    ): PokemonListResponse

    @GET("pokemon/{pokemonNumber}")
    suspend fun getPokemonDetail(
        @Path("pokemonNumber") pokemonNo: Int,
    ): PokemonDetailResponse

}