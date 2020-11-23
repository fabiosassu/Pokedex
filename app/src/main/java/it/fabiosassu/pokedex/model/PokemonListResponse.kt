package it.fabiosassu.pokedex.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import it.fabiosassu.pokedex.util.lastPathSegmentAsAnInt

@JsonClass(generateAdapter = true)
data class PokemonListResponse(
    @Json(name = "count")
    val count: Int?,
    @Json(name = "next")
    val next: String?,
    @Json(name = "previous")
    val previous: String?,
    @Json(name = "results")
    val results: List<Pokemon>?
)

@JsonClass(generateAdapter = true)
data class Pokemon(
    var id: Int?,
    @Json(name = "name")
    val name: String,
    @Json(name = "url")
    val url: String?,
) {
    val number
        get() = url?.lastPathSegmentAsAnInt()
}