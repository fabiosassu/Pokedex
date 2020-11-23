package it.fabiosassu.pokedex.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import it.fabiosassu.pokedex.database.converter.SpritesEntityTypeConverter
import it.fabiosassu.pokedex.database.converter.StatEntityListTypesConverters
import it.fabiosassu.pokedex.database.converter.TypeEntityListTypesConverter
import it.fabiosassu.pokedex.database.dao.PokemonDao
import it.fabiosassu.pokedex.database.dao.PokemonDetailDao
import it.fabiosassu.pokedex.database.dao.PokemonRemoteKeyDao
import it.fabiosassu.pokedex.database.model.PokemonDetailEntity
import it.fabiosassu.pokedex.database.model.PokemonEntity
import it.fabiosassu.pokedex.database.model.PokemonRemoteKeyEntity

/**
 * Database schema used by the DbPokemonRepository
 */
@Database(
    entities = [PokemonEntity::class, PokemonRemoteKeyEntity::class, PokemonDetailEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(
    StatEntityListTypesConverters::class,
    TypeEntityListTypesConverter::class,
    SpritesEntityTypeConverter::class
)
abstract class PokemonDb : RoomDatabase() {
    companion object {
        fun create(context: Context, useInMemory: Boolean): PokemonDb {
            val databaseBuilder = if (useInMemory) {
                Room.inMemoryDatabaseBuilder(context, PokemonDb::class.java)
            } else {
                Room.databaseBuilder(context, PokemonDb::class.java, "pokemon.db")
            }
            return databaseBuilder
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun pokemons(): PokemonDao

    abstract fun pokemonDetails(): PokemonDetailDao

    abstract fun remoteKeys(): PokemonRemoteKeyDao
}