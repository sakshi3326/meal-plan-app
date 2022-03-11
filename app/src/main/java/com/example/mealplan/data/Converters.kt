package com.example.mealplan.data

import androidx.room.TypeConverter

class Converters {
    @TypeConverter
    fun fromList(list: List<Int>): String {
        return list.toString()
    }

    @TypeConverter
    fun toList(ints: String): List<Int> {
        return ints.split(",").map { it.trim().toInt() }
    }
}