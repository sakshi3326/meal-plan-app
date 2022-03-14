package com.example.mealplan.data

import androidx.room.*
import java.io.Serializable

@Entity(indices = [Index(value = ["description"], unique = true)])
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var description: String,
    var notes: String? = null,
    var url: String? = null,
) : Serializable

data class RecipeWithItems(
    @Embedded val recipe: Recipe,
    @Relation(
        parentColumn="id",
        entityColumn = "recipe_id"
    )
    val items: List<FoodItemData>
)