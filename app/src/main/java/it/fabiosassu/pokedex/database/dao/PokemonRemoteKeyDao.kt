package it.fabiosassu.pokedex.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import it.fabiosassu.pokedex.database.model.PokemonRemoteKeyEntity

@Dao
interface PokemonRemoteKeyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePokeomnKeys(pokemonRemoteKey: PokemonRemoteKeyEntity)

    @Query("SELECT * FROM remote_keys ORDER BY id DESC")
    suspend fun getPokemonKeys(): List<PokemonRemoteKeyEntity>

}