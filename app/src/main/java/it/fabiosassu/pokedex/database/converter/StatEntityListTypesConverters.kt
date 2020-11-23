package it.fabiosassu.pokedex.database.converter

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import it.fabiosassu.pokedex.database.model.StatEntity
import it.fabiosassu.pokedex.model.Stat

/**
 * Type converter for lists of [Stat] objects
 */
class StatEntityListTypesConverters {
    var moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @TypeConverter
    fun toJson(value: List<StatEntity>?): String? {
        val type = Types.newParameterizedType(List::class.java, StatEntity::class.java)
        val adapter: JsonAdapter<List<StatEntity>> = moshi.adapter(type)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun fromJson(value: String): List<StatEntity>? {
        val type = Types.newParameterizedType(List::class.java, StatEntity::class.java)
        val adapter: JsonAdapter<List<StatEntity>> = moshi.adapter(type)
        return adapter.fromJson(value)
    }

}