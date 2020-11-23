package it.fabiosassu.pokedex.database.converter

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import it.fabiosassu.pokedex.database.model.TypeEntity

/**
 * Type converter for lists of [Types] objects
 */
class TypeEntityListTypesConverter {
    var moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @TypeConverter
    fun toJson(value: List<TypeEntity>?): String? {
        val type = Types.newParameterizedType(List::class.java, TypeEntity::class.java)
        val adapter: JsonAdapter<List<TypeEntity>> = moshi.adapter(type)
        return adapter.toJson(value)
    }

    @TypeConverter
    fun fromJson(value: String): List<TypeEntity>? {
        val type = Types.newParameterizedType(List::class.java, TypeEntity::class.java)
        val adapter: JsonAdapter<List<TypeEntity>> = moshi.adapter(type)
        return adapter.fromJson(value)
    }

}