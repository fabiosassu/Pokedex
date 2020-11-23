package it.fabiosassu.pokedex.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.LoadType.*
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import it.fabiosassu.pokedex.api.PokemonApi
import it.fabiosassu.pokedex.database.PokemonDb
import it.fabiosassu.pokedex.database.dao.PokemonDao
import it.fabiosassu.pokedex.database.dao.PokemonRemoteKeyDao
import it.fabiosassu.pokedex.database.model.PokemonEntity
import it.fabiosassu.pokedex.database.model.PokemonRemoteKeyEntity
import it.fabiosassu.pokedex.database.toPokemonEntityList
import retrofit2.HttpException
import java.io.IOException

@OptIn(ExperimentalPagingApi::class)
class PageKeyedRemoteMediator(
    private val db: PokemonDb,
    private val pokemonApi: PokemonApi,
    private val pokemonName: String?,
) : RemoteMediator<Int, PokemonEntity>() {
    private val pokemonDao: PokemonDao = db.pokemons()
    private val remoteKeyDao: PokemonRemoteKeyDao = db.remoteKeys()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PokemonEntity>
    ): MediatorResult {
        try {
            // Get the closest item from PagingState that we want to load data around.
            val loadKey = when (loadType) {
                REFRESH -> null
                PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                APPEND -> {
                    state.lastItemOrNull()
                        ?: return MediatorResult.Success(endOfPaginationReached = true)
                    getPokemonKeys()
                }
            }

            val data = pokemonApi.getPokemons(
                pokemonName = pokemonName ?: "",
                offset = loadKey?.after?.toIntOrNull(),
                limit = when (loadType) {
                    REFRESH -> state.config.initialLoadSize
                    else -> state.config.pageSize
                }
            )

            val items = data.results

            val previous = items?.minByOrNull { it.number ?: 0 }?.number?.toString()
            val next = items?.maxByOrNull { it.number ?: 0 }?.number?.toString()

            db.withTransaction {
                if (previous != null && next != null) {
                    remoteKeyDao.savePokeomnKeys(
                        PokemonRemoteKeyEntity(
                            0,
                            next,
                            previous
                        )
                    )
                    items.forEach { item -> item.id = item.number!! }
                    items.let {
                        pokemonDao.insertAll(it.toPokemonEntityList())
                    }
                }
            }

            return MediatorResult.Success(endOfPaginationReached = data.next == null)
        } catch (e: IOException) {
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getPokemonKeys(): PokemonRemoteKeyEntity? =
        remoteKeyDao.getPokemonKeys().firstOrNull()
}
