package com.example.mealplan.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Recipe(
    @PrimaryKey
    val name: String,
    val description: String? = null,
    val notes: String? = null,
    val url: String? = null,
    val amounts: List<Int>? = null,
    val ingredients: List<Int>? = null
) : Serializable