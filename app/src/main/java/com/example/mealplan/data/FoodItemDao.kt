package com.example.mealplan.data

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodItemDao {
    @Insert(onConflict = REPLACE)
    suspend fun insert(foodItemData: FoodItemData): Long

    @Delete
    suspend fun delete(foodItemData: FoodItemData)

    @Query("SELECT * FROM FoodItemData")
    fun getAllItems(): Flow<List<FoodItemData>>

    @Query("SELECT * FROM FoodItemData WHERE name LIKE :name")
    fun searchItemsByName(name: String): Flow<FoodItemData?>

    @Transaction
    @Query("SELECT * FROM FoodItemData")
    fun getItemNutrients(): List<FoodItemDataWithNutrients>
}