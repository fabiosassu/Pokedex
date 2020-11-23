package it.fabiosassu.pokedex.database.converter

import androidx.room.TypeConverter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import it.fabiosassu.pokedex.database.model.SpritesEntity

/**
 * Type converter for lists of [SpritesEntity] objects
 */
class SpritesEntityTypeConverter {
    var moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @TypeConverter
    fun toJson(value: SpritesEntity?): String? {
        val adapter = moshi.adapter(SpritesEntity::class.java)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun fromJson(value: String): SpritesEntity? {
        val adapter = moshi.adapter(SpritesEntity::class.java)
        return adapter.fromJson(value)
    }

}