package it.fabiosassu.pokedex.database

import it.fabiosassu.pokedex.database.model.*
import it.fabiosassu.pokedex.model.*

fun PokemonEntity.toPokemon(): Pokemon = Pokemon(this.id, this.name, this.url)

fun List<Pokemon>.toPokemonEntityList(): List<PokemonEntity> = this.map { it.toPokemonEntity() }

fun Pokemon.toPokemonEntity(): PokemonEntity = PokemonEntity(this.id, this.name, this.url)

fun PokemonDetailEntity?.toPokemonDetailResponse(): PokemonDetailResponse? {
    return this?.let {
        PokemonDetailResponse(
            this.baseExperience,
            this.height,
            this.id,
            this.isDefault,
            this.locationAreaEncounters,
            this.name,
            this.order,
            this.sprites?.toSpriteResponse(),
            this.stats?.toStatListResponse(),
            this.types?.toTypeListResponse(),
            this.weight
        )
    }
}

fun List<StatEntity>?.toStatListResponse(): List<Stat>? = this?.map { it.toStatResponse() }

fun StatEntity?.toStatResponse(): Stat =
    Stat(this?.baseStat, this?.effort, this?.stat?.toStatXResponse())

fun StatXEntity?.toStatXResponse(): StatX = StatX(this?.name, this?.url)

fun SpritesEntity?.toSpriteResponse(): Sprites =
    Sprites(
        this?.backDefault,
        this?.backFemale,
        this?.backShiny,
        this?.backShinyFemale,
        this?.frontDefault,
        this?.frontFemale,
        this?.frontShiny,
        this?.frontShinyFemale,
        null,
        null
    )

fun List<TypeEntity>?.toTypeListResponse(): List<Type>? = this?.map { it.toTypeResponse() }

fun TypeEntity.toTypeResponse(): Type = Type(this.slot, this.type?.toTypeXResponse())

fun TypeXEntity.toTypeXResponse(): TypeX = TypeX(this.name, this.url)

fun PokemonDetailResponse?.toPokemonDetailEntity(): PokemonDetailEntity {
    return PokemonDetailEntity(
        this?.baseExperience,
        this?.height,
        this?.id,
        this?.isDefault,
        this?.locationAreaEncounters,
        this?.name,
        this?.order,
        this?.sprites?.toSpriteEntity(),
        this?.stats?.toStatEntityList(),
        this?.types?.toTypeListEntity(),
        this?.weight
    )
}

fun List<Stat>?.toStatEntityList(): List<StatEntity>? = this?.map { it.toStatEntity() }

fun Stat?.toStatEntity(): StatEntity =
    StatEntity(this?.baseStat, this?.effort, this?.stat?.toStatXEntity())

fun StatX?.toStatXEntity(): StatXEntity = StatXEntity(this?.name, this?.url)

fun Sprites?.toSpriteEntity(): SpritesEntity = SpritesEntity(
    this?.backDefault,
    this?.backFemale,
    this?.backShiny,
    this?.backShinyFemale,
    this?.frontDefault,
    this?.frontFemale,
    this?.frontShiny,
    this?.frontShinyFemale
)

fun List<Type>?.toTypeListEntity(): List<TypeEntity>? = this?.map { it.toTypeEntity() }

fun Type?.toTypeEntity(): TypeEntity = TypeEntity(this?.slot, this?.type?.toTypeXEntity())

fun TypeX?.toTypeXEntity(): TypeXEntity = TypeXEntity(this?.name, this?.url)