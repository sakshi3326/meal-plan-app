package com.example.mealplan.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface NutrientDao {
    @Insert
    suspend fun insert(nutrient: NutrientData)

    @Delete
    suspend fun delete(nutrient: NutrientData)

    @Query("DELETE FROM NutrientData WHERE id = :id")
    fun deleteNutrientById(id: Int)

    @Query("SELECT * FROM NutrientData WHERE id = :id LIMIT 1")
    fun searchNutrientById(id: Int): Flow<NutrientData>
}