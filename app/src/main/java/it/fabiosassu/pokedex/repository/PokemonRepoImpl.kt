package it.fabiosassu.pokedex.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.map
import it.fabiosassu.pokedex.api.PokemonApi
import it.fabiosassu.pokedex.database.PokemonDb
import it.fabiosassu.pokedex.database.toPokemon
import it.fabiosassu.pokedex.database.toPokemonDetailEntity
import it.fabiosassu.pokedex.database.toPokemonDetailResponse
import it.fabiosassu.pokedex.model.PokemonDetailResponse
import kotlinx.coroutines.flow.map

class PokemonRepoImpl constructor(
    private val db: PokemonDb,
    private val pokemonApi: PokemonApi,
) : PokemonRepo {

    companion object;

    /**
     * method that retrieves the list of pokemons
     *
     * @return PokemonListResponse the list of pokemons
     */
    override suspend fun getPokemonList(pokemonName: String?, limit: Int, offset: Int?) = Pager(
        PagingConfig(pageSize = limit),
        remoteMediator = PageKeyedRemoteMediator(db, pokemonApi, pokemonName = pokemonName),
        pagingSourceFactory = { db.pokemons().getAllPokemon() }
    ).flow.map { it.map { item -> item.toPokemon() } }

    /**
     * method to retrieve pokemon info from a given pokemon id.
     *
     * @param pokemonNumber Int the id of the pokemon we want to retrieve
     * @return Data the details information of the given pokemon id.
     */
    override suspend fun getPokemonDetails(pokemonNumber: Int): PokemonDetailResponse? {

        val pokemonDetailDao = db.pokemonDetails()
        // if we don't have the pokemon saved in the db...
        if (pokemonDetailDao.getPokemonById(pokemonNumber) == null) {
            // ...try fetching it from ws
            val pokemonDetail = pokemonApi.getPokemonDetail(pokemonNumber)
            // save into db
            pokemonDetailDao.insertPokemon(pokemonDetail.toPokemonDetailEntity())
        }
        // retrieve from db
        return pokemonDetailDao.getPokemonById(pokemonNumber)?.toPokemonDetailResponse()
    }

}