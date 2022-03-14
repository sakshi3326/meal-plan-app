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
}