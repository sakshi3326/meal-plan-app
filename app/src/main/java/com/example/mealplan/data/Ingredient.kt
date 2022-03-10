package com.example.mealplan.data

import com.squareup.moshi.Json

data class Nutrient(
    //There is a nutrient named Energy, that is Calories.
    @Json(name = "nutrientName") val name: String,//Nutrient name Ex: Protein, total lipid (fat)
    @Json(name = "unitName") val unit: String, //Ex. G for grams, (why its capitalize, no idea.)
    @Json(name = "value") val nutritionVal: Double,

    //method of calculation Ex. text: "Calculated from a daily value percentage per serving size measure"
    @Json(name = "deprivationDescription") val calc: String?
)

data class Food(
    //Name of food, who owns it, and the brand name
    @Json(name = "lowercaseDescription") val name: String,
    val brandOwner: String?,
    @Json(name = "brandName") val brand: String?,

    //What's in it?
    val ingredients: String?,

    //Raw amount Ex. 3 cups
    @Json(name = "householdServingFullText") val serving: String?,

    //Protein, Fats, Sugar, etc
    @Json(name = "foodNutrients") val nutrients: List<Nutrient>,

    //Packaged, RawValue ex. Bananas
    @Json(name = "foodCategory") val category: String,

    //Amount of food
    @Json(name = "packageWeight") val packaged: String?,
    @Json(name = "servingSizeUnit") val sizeUnit: String?, // Ex. g for grams
    val servingSize: Double?
)

data class IngredientsList(
    @Json(name = "foods") val results: List<Food>
)