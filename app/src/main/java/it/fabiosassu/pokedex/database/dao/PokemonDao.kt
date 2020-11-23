package it.fabiosassu.pokedex.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import it.fabiosassu.pokedex.database.model.PokemonEntity

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pokemons: List<PokemonEntity>)

    @Query("SELECT * FROM pokemons ORDER BY id ASC")
    fun getAllPokemon(): PagingSource<Int, PokemonEntity>

    @Query("SELECT * FROM pokemons WHERE name LIKE :name ORDER BY name ASC")
    fun pokemonByName(name: String): PagingSource<Int, PokemonEntity>

}
