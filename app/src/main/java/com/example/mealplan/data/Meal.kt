package com.example.mealplan.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import java.io.Serializable

@Entity(primaryKeys = ["name", "date"])
data class Meal(
    val name: String,
    val date: String,
    val description: String? = null,
    val notes: String? = null,
    val url: String? = null,
    val amounts: List<Int>? = null,
    val ingredients: List<Int>? = null
) : Serializable