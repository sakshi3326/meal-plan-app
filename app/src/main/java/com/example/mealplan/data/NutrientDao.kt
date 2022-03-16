package com.example.mealplan.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NutrientDao {
    suspend fun insert(nutrient: NutrientData): NutrientData {
        nutrient.id = _insert(nutrient)
        return nutrient
    }

    @Insert
    suspend fun _insert(nutrient: NutrientData): Long

    @Delete
    suspend fun delete(nutrient: NutrientData)

    @Query("DELETE FROM NutrientData WHERE id = :id")
    fun deleteNutrientById(id: Long)

    @Query("SELECT * FROM NutrientData WHERE id = :id LIMIT 1")
    fun searchNutrientById(id: Long): Flow<NutrientData>

    @Query ("SELECT * FROM NutrientData WHERE item_id = :id")
    fun searchNutrientByItemId(id: Long): List<NutrientData>?

    @Query("SELECT nd.item_id, fd.id, fd.meal_id, nd.name, nd.nutritionVal, nd.unit FROM FoodItemData AS fd INNER JOIN NutrientData AS nd ON nd.item_id = fd.id WHERE fd.meal_id = :id ")
    fun searchAllNutrientsForMeal(id: Long): List<NutrientData>?

    @Query("SELECT nd.item_id, fd.id, fd.recipe_id, nd.name, nd.nutritionVal, nd.unit FROM FoodItemData AS fd INNER JOIN NutrientData AS nd ON nd.item_id = fd.id WHERE fd.recipe_id = :id ")
    fun searchAllNutrientsForRecipe(id: Long): List<NutrientData>?
}