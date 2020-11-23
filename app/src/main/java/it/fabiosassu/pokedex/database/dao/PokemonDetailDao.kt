package it.fabiosassu.pokedex.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import it.fabiosassu.pokedex.database.model.PokemonDetailEntity

@Dao
interface PokemonDetailDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemon: PokemonDetailEntity)

    @Query("SELECT * FROM pokemon_detail WHERE id = :id")
    fun getPokemonById(id: Int): PokemonDetailEntity?

}
