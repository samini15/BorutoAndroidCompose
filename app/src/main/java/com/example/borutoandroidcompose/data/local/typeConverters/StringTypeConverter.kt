package com.example.borutoandroidcompose.data.local.typeConverters

import androidx.room.TypeConverter

class StringTypeConverter {

    private val separator = ","

    @TypeConverter
    fun convertListToString(list: List<String>) : String {
        val stringBuilder = StringBuilder()
        for (item in list) {
            stringBuilder.append(item).append(separator)
        }
        // remove last separator
        stringBuilder.setLength(stringBuilder.length - separator.length)
        return stringBuilder.toString()
    }

    @TypeConverter
    fun convertStringToList(string: String) : List<String> =
        string.split(separator)
}