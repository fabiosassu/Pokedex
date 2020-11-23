package it.fabiosassu.pokedex.util

import android.net.Uri
import java.util.*

fun String.lastPathSegmentAsAnInt() = Uri.parse(this).lastPathSegment?.toIntOrNull()

fun List<String?>?.toCommaSeparatedString(
    separatorChar: Char? = ',',
    capitalizedWords: Boolean? = false
): String {
    val sb = StringBuilder()
    var tmpSeparator = ""
    this?.forEach {
        val element = if (capitalizedWords == true) it?.capitalize(Locale.getDefault()) else it
        sb.append(tmpSeparator).append(element)
        tmpSeparator = "$separatorChar "
    }
    return sb.toString()
}