package it.fabiosassu.pokedex.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author fabiosassu
 * @version 1.0
 */
@JsonClass(generateAdapter = true)
data class PokemonDetailResponse(
    @Json(name = "base_experience")
    var baseExperience: Int?,
    @Json(name = "height")
    var height: Int?,
    @Json(name = "id")
    var id: Int?,
    @Json(name = "is_default")
    var isDefault: Boolean?,
    @Json(name = "location_area_encounters")
    var locationAreaEncounters: String?,
    @Json(name = "name")
    var name: String?,
    @Json(name = "order")
    var order: Int?,
    @Json(name = "sprites")
    var sprites: Sprites?,
    @Json(name = "stats")
    var stats: List<Stat>?,
    @Json(name = "types")
    var types: List<Type>?,
    @Json(name = "weight")
    var weight: Int?,
    @Json(name = "abilities")
    var abilities: List<Ability>? = null,
    @Json(name = "species")
    var species: Species? = null,
    @Json(name = "moves")
    var moves: List<Move>? = null,
    @Json(name = "held_items")
    var heldItems: List<Any>? = null,
    @Json(name = "game_indices")
    var gameIndices: List<GameIndice>? = null,
    @Json(name = "forms")
    var forms: List<Form>? = null
)

@JsonClass(generateAdapter = true)
data class Ability(
    @Json(name = "ability")
    val ability: AbilityX?,
    @Json(name = "is_hidden")
    val isHidden: Boolean?,
    @Json(name = "slot")
    val slot: Int?
)

@JsonClass(generateAdapter = true)
data class Form(
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?
)

@JsonClass(generateAdapter = true)
data class GameIndice(
    @Json(name = "game_index")
    val gameIndex: Int?,
    @Json(name = "version")
    val version: Version?
)

@JsonClass(generateAdapter = true)
data class Move(
    @Json(name = "move")
    val move: MoveX?,
    @Json(name = "version_group_details")
    val versionGroupDetails: List<VersionGroupDetail>?
)

@JsonClass(generateAdapter = true)
data class Species(
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?
)

@JsonClass(generateAdapter = true)
data class Sprites(
    @Json(name = "back_default")
    val backDefault: String?,
    @Json(name = "back_female")
    val backFemale: String?,
    @Json(name = "back_shiny")
    val backShiny: String?,
    @Json(name = "back_shiny_female")
    val backShinyFemale: String?,
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_female")
    val frontFemale: String?,
    @Json(name = "front_shiny")
    val frontShiny: String?,
    @Json(name = "front_shiny_female")
    val frontShinyFemale: String?,
    @Json(name = "other")
    val other: Other?,
    @Json(name = "versions")
    val versions: Versions?
)

@JsonClass(generateAdapter = true)
data class Stat(
    @Json(name = "base_stat")
    val baseStat: Int?,
    @Json(name = "effort")
    val effort: Int?,
    @Json(name = "stat")
    val stat: StatX?
)

@JsonClass(generateAdapter = true)
data class Type(
    @Json(name = "slot")
    val slot: Int?,
    @Json(name = "type")
    val type: TypeX?
)

@JsonClass(generateAdapter = true)
data class AbilityX(
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?
)

@JsonClass(generateAdapter = true)
data class Version(
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?
)

@JsonClass(generateAdapter = true)
data class MoveX(
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?
)

@JsonClass(generateAdapter = true)
data class VersionGroupDetail(
    @Json(name = "level_learned_at")
    val levelLearnedAt: Int?,
    @Json(name = "move_learn_method")
    val moveLearnMethod: MoveLearnMethod?,
    @Json(name = "version_group")
    val versionGroup: VersionGroup?
)

@JsonClass(generateAdapter = true)
data class MoveLearnMethod(
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?
)

@JsonClass(generateAdapter = true)
data class VersionGroup(
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?
)

@JsonClass(generateAdapter = true)
data class Other(
    @Json(name = "dream_world")
    val dreamWorld: DreamWorld?,
    @Json(name = "official-artwork")
    val officialArtwork: OfficialArtwork?
)

@JsonClass(generateAdapter = true)
data class Versions(
    @Json(name = "generation-i")
    val generationI: GenerationI?,
    @Json(name = "generation-ii")
    val generationIi: GenerationIi?,
    @Json(name = "generation-iii")
    val generationIii: GenerationIii?,
    @Json(name = "generation-iv")
    val generationIv: GenerationIv?,
    @Json(name = "generation-v")
    val generationV: GenerationV?,
    @Json(name = "generation-vi")
    val generationVi: GenerationVi?,
    @Json(name = "generation-vii")
    val generationVii: GenerationVii?,
    @Json(name = "generation-viii")
    val generationViii: GenerationViii?
)

@JsonClass(generateAdapter = true)
data class DreamWorld(
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_female")
    val frontFemale: String?
)

@JsonClass(generateAdapter = true)
data class OfficialArtwork(
    @Json(name = "front_default")
    val frontDefault: String?
)

@JsonClass(generateAdapter = true)
data class GenerationI(
    @Json(name = "red-blue")
    val redBlue: RedBlue?,
    @Json(name = "yellow")
    val yellow: Yellow?
)

@JsonClass(generateAdapter = true)
data class GenerationIi(
    @Json(name = "crystal")
    val crystal: Crystal?,
    @Json(name = "gold")
    val gold: Gold?,
    @Json(name = "silver")
    val silver: Silver?
)

@JsonClass(generateAdapter = true)
data class GenerationIii(
    @Json(name = "emerald")
    val emerald: Emerald?,
    @Json(name = "firered-leafgreen")
    val fireredLeafgreen: FireredLeafgreen?,
    @Json(name = "ruby-sapphire")
    val rubySapphire: RubySapphire?
)

@JsonClass(generateAdapter = true)
data class GenerationIv(
    @Json(name = "diamond-pearl")
    val diamondPearl: DiamondPearl?,
    @Json(name = "heartgold-soulsilver")
    val heartgoldSoulsilver: HeartgoldSoulsilver?,
    @Json(name = "platinum")
    val platinum: Platinum?
)

@JsonClass(generateAdapter = true)
data class GenerationV(
    @Json(name = "black-white")
    val blackWhite: BlackWhite?
)

@JsonClass(generateAdapter = true)
data class GenerationVi(
    @Json(name = "omegaruby-alphasapphire")
    val omegarubyAlphasapphire: OmegarubyAlphasapphire?,
    @Json(name = "x-y")
    val xY: XY?
)

@JsonClass(generateAdapter = true)
data class GenerationVii(
    @Json(name = "icons")
    val icons: Icons?,
    @Json(name = "ultra-sun-ultra-moon")
    val ultraSunUltraMoon: UltraSunUltraMoon?
)

@JsonClass(generateAdapter = true)
data class GenerationViii(
    @Json(name = "icons")
    val icons: IconsX?
)

@JsonClass(generateAdapter = true)
data class RedBlue(
    @Json(name = "back_default")
    val backDefault: String?,
    @Json(name = "back_gray")
    val backGray: String?,
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_gray")
    val frontGray: String?
)

@JsonClass(generateAdapter = true)
data class Yellow(
    @Json(name = "back_default")
    val backDefault: String?,
    @Json(name = "back_gray")
    val backGray: String?,
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_gray")
    val frontGray: String?
)

@JsonClass(generateAdapter = true)
data class Crystal(
    @Json(name = "back_default")
    val backDefault: String?,
    @Json(name = "back_shiny")
    val backShiny: String?,
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_shiny")
    val frontShiny: String?
)

@JsonClass(generateAdapter = true)
data class Gold(
    @Json(name = "back_default")
    val backDefault: String?,
    @Json(name = "back_shiny")
    val backShiny: String?,
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_shiny")
    val frontShiny: String?
)

@JsonClass(generateAdapter = true)
data class Silver(
    @Json(name = "back_default")
    val backDefault: String?,
    @Json(name = "back_shiny")
    val backShiny: String?,
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_shiny")
    val frontShiny: String?
)

@JsonClass(generateAdapter = true)
data class Emerald(
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_shiny")
    val frontShiny: String?
)

@JsonClass(generateAdapter = true)
data class FireredLeafgreen(
    @Json(name = "back_default")
    val backDefault: String?,
    @Json(name = "back_shiny")
    val backShiny: String?,
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_shiny")
    val frontShiny: String?
)

@JsonClass(generateAdapter = true)
data class RubySapphire(
    @Json(name = "back_default")
    val backDefault: String?,
    @Json(name = "back_shiny")
    val backShiny: String?,
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_shiny")
    val frontShiny: String?
)

@JsonClass(generateAdapter = true)
data class DiamondPearl(
    @Json(name = "back_default")
    val backDefault: String?,
    @Json(name = "back_female")
    val backFemale: String?,
    @Json(name = "back_shiny")
    val backShiny: String?,
    @Json(name = "back_shiny_female")
    val backShinyFemale: String?,
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_female")
    val frontFemale: String?,
    @Json(name = "front_shiny")
    val frontShiny: String?,
    @Json(name = "front_shiny_female")
    val frontShinyFemale: String?
)

@JsonClass(generateAdapter = true)
data class HeartgoldSoulsilver(
    @Json(name = "back_default")
    val backDefault: String?,
    @Json(name = "back_female")
    val backFemale: String?,
    @Json(name = "back_shiny")
    val backShiny: String?,
    @Json(name = "back_shiny_female")
    val backShinyFemale: String?,
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_female")
    val frontFemale: String?,
    @Json(name = "front_shiny")
    val frontShiny: String?,
    @Json(name = "front_shiny_female")
    val frontShinyFemale: String?
)

@JsonClass(generateAdapter = true)
data class Platinum(
    @Json(name = "back_default")
    val backDefault: String?,
    @Json(name = "back_female")
    val backFemale: String?,
    @Json(name = "back_shiny")
    val backShiny: String?,
    @Json(name = "back_shiny_female")
    val backShinyFemale: String?,
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_female")
    val frontFemale: String?,
    @Json(name = "front_shiny")
    val frontShiny: String?,
    @Json(name = "front_shiny_female")
    val frontShinyFemale: String?
)

@JsonClass(generateAdapter = true)
data class BlackWhite(
    @Json(name = "animated")
    val animated: Animated?,
    @Json(name = "back_default")
    val backDefault: String?,
    @Json(name = "back_female")
    val backFemale: String?,
    @Json(name = "back_shiny")
    val backShiny: String?,
    @Json(name = "back_shiny_female")
    val backShinyFemale: String?,
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_female")
    val frontFemale: String?,
    @Json(name = "front_shiny")
    val frontShiny: String?,
    @Json(name = "front_shiny_female")
    val frontShinyFemale: String?
)

@JsonClass(generateAdapter = true)
data class Animated(
    @Json(name = "back_default")
    val backDefault: String?,
    @Json(name = "back_female")
    val backFemale: String?,
    @Json(name = "back_shiny")
    val backShiny: String?,
    @Json(name = "back_shiny_female")
    val backShinyFemale: String?,
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_female")
    val frontFemale: String?,
    @Json(name = "front_shiny")
    val frontShiny: String?,
    @Json(name = "front_shiny_female")
    val frontShinyFemale: String?
)

@JsonClass(generateAdapter = true)
data class OmegarubyAlphasapphire(
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_female")
    val frontFemale: String?,
    @Json(name = "front_shiny")
    val frontShiny: String?,
    @Json(name = "front_shiny_female")
    val frontShinyFemale: String?
)

@JsonClass(generateAdapter = true)
data class XY(
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_female")
    val frontFemale: String?,
    @Json(name = "front_shiny")
    val frontShiny: String?,
    @Json(name = "front_shiny_female")
    val frontShinyFemale: String?
)

@JsonClass(generateAdapter = true)
data class Icons(
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_female")
    val frontFemale: String?
)

@JsonClass(generateAdapter = true)
data class UltraSunUltraMoon(
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_female")
    val frontFemale: String?,
    @Json(name = "front_shiny")
    val frontShiny: String?,
    @Json(name = "front_shiny_female")
    val frontShinyFemale: String?
)

@JsonClass(generateAdapter = true)
data class IconsX(
    @Json(name = "front_default")
    val frontDefault: String?,
    @Json(name = "front_female")
    val frontFemale: String?
)

@JsonClass(generateAdapter = true)
data class StatX(
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?
)

@JsonClass(generateAdapter = true)
data class TypeX(
    @Json(name = "name")
    val name: String?,
    @Json(name = "url")
    val url: String?
)