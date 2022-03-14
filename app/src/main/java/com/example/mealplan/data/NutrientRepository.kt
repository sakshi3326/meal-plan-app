package com.example.mealplan.data

class NutrientRepository (private val dao: NutrientDao) {
    suspend fun insertNutrient(nutrient: NutrientData): NutrientData {
        return dao.insert(nutrient)
    }
    suspend fun removeNutrient(nutrient: NutrientData) = dao.delete(nutrient)
    fun searchNutrientById(id: Long) = dao.searchNutrientById(id)
    fun deleteNutrientById(id: Long) = dao.deleteNutrientById(id)
}