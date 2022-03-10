package com.example.mealplan.data

import java.io.Serializable

data class Recipe(
    val name: String,
    //val ingredients: MutableList<Ingredient>,
    //val nutrition: Nutrition,
    val notes: String?,
    val url: String?
) : Serializable
