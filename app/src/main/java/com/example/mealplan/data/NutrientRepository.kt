package com.example.mealplan.data

class NutrientRepository (private val dao: NutrientDao) {
    suspend fun insertNutrient(nutrient: NutrientData) = dao.insert(nutrient)
    suspend fun removeNutrient(nutrient: NutrientData) = dao.delete(nutrient)
}