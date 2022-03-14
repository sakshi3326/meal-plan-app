package com.example.mealplan.data

import android.util.Log
import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodItemDao {
    suspend fun insert(foodItemData: FoodItemData): FoodItemData {
        foodItemData.id = _insert(foodItemData)
        return foodItemData
    }

    @Insert(onConflict = REPLACE)
    suspend fun _insert(foodItemData: FoodItemData): Long

    @Delete
    suspend fun delete(foodItemData: FoodItemData)

    @Query("SELECT * FROM FoodItemData")
    fun getAllItems(): Flow<List<FoodItemData>>

    @Query("SELECT * FROM FoodItemData WHERE name LIKE :name")
    fun searchItemsByName(name: String): Flow<FoodItemData?>

    @Query("SELECT * FROM FoodItemData WHERE meal_id = :mealID")
    fun searchItemsByMealID(mealID: Long): Flow<List<FoodItemData>?>

    @Transaction
    @Query("SELECT * FROM FoodItemData")
    fun getItemNutrients(): List<FoodItemDataWithNutrients>
}