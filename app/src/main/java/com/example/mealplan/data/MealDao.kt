package com.example.mealplan.data

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow

@Dao
interface MealDao {
    suspend fun update(meal: Meal): Meal {
        _update(meal)
        return meal
    }

    @Update
    suspend fun _update(meal: Meal)

    suspend fun insert(meal: Meal): Meal {
        meal.id = _insert(meal)
        return meal
    }

    @Insert(onConflict = REPLACE) //equivalent to saying this is just an update/insert function
    suspend fun _insert(meal: Meal): Long

    @Delete
    suspend fun delete(meal: Meal)

    @Query("SELECT * FROM Meal")
    fun getAllMeals(): Flow<List<Meal>>

    @Query("SELECT * FROM Meal WHERE date LIKE :date")
    fun searchMealByDate(date: String): Flow<List<Meal>>

    @Transaction
    @Query("SELECT * FROM Meal")
    fun getItems(): List<MealWithItems>

    @Query("SELECT * FROM Meal WHERE date LIKE :date AND name LIKE :name")
    fun searchMealByDateAndName(date: String, name: String): Meal
}