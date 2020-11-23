package it.fabiosassu.pokedex.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * @author fabiosassu
 * @version 1.0
 */
@Entity(tableName = "pokemon_detail")
data class PokemonDetailEntity(
    var baseExperience: Int?,
    var height: Int?,
    @PrimaryKey
    var id: Int?,
    var isDefault: Boolean?,
    var locationAreaEncounters: String?,
    var name: String?,
    var order: Int?,
    var sprites: SpritesEntity?,
    var stats: List<StatEntity>?,
    var types: List<TypeEntity>?,
    var weight: Int?,
)

data class SpritesEntity(
    val backDefault: String?,
    val backFemale: String?,
    val backShiny: String?,
    val backShinyFemale: String?,
    val frontDefault: String?,
    val frontFemale: String?,
    val frontShiny: String?,
    val frontShinyFemale: String?,
)

data class StatEntity(
    val baseStat: Int?,
    val effort: Int?,
    val stat: StatXEntity?
)

data class TypeEntity(
    val slot: Int?,
    val type: TypeXEntity?
)

data class StatXEntity(
    val name: String?,
    val url: String?
)

data class TypeXEntity(
    val name: String?,
    val url: String?
)