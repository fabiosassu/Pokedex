package it.fabiosassu.pokedex.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "remote_keys")
data class PokemonRemoteKeyEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val after: String?,
    val before: String?
)