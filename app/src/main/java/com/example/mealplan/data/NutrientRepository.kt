package com.example.mealplan.data

import kotlinx.coroutines.flow.Flow

class NutrientRepository (private val dao: NutrientDao) {
    suspend fun insertNutrient(nutrient: NutrientData): NutrientData {
        return dao.insert(nutrient)
    }
    suspend fun removeNutrient(nutrient: NutrientData) = dao.delete(nutrient)
    fun searchNutrientById(id: Long) = dao.searchNutrientById(id)
    fun deleteNutrientById(id: Long) = dao.deleteNutrientById(id)
    suspend fun searchNutrientByItemId(item_Id: Long): List<NutrientData>? {
        return dao.searchNutrientByItemId(item_Id)
    }
    suspend fun searchAllNutrientsForMeal(meal_id: Long): List<NutrientData>? {
        return dao.searchAllNutrientsForMeal(meal_id)
    }
}