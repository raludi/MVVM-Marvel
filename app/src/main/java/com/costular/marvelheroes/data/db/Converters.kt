package com.costular.marvelheroes.data.db

import android.arch.persistence.room.TypeConverter

class Converters {

        @TypeConverter
        fun arrayToString(data: MutableList<String>): String =
                data.joinToString()

        @TypeConverter
        fun StringToArray(data: String): MutableList<String> =
                data.replace("\\s".toRegex(), "").split(",").toMutableList()

}