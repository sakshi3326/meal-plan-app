package com.example.mealplan.data

import androidx.room.*
import java.io.Serializable

@Entity(indices = [Index(value = ["name", "date"], unique = true)])
//ensure that name and date are the unique identifiers but an id is still the primary key
data class Meal(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    val name: String,
    val date: String,
    var description: String? = null,
    var notes: String? = null,
    var url: String? = null,
) : Serializable

data class MealWithItems(
    @Embedded val meal: Meal,
    @Relation(
        parentColumn="id",
        entityColumn = "meal_id"
    )
    val items: List<FoodItemData>
)