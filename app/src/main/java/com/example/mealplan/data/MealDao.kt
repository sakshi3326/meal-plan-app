package com.example.mealplan.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    @Insert(onConflict = REPLACE) //equivalent to saying this is just an update/insert function
    suspend fun insert(meal: Meal)

    @Delete
    suspend fun delete(meal: Meal)

    @Query("SELECT * FROM Meal")
    fun getAllMeals(): Flow<List<Meal>>

    @Query("SELECT * FROM Meal WHERE date = :date")
    fun searchMealByDate(date: String): Flow<List<Meal>?>
}