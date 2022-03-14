package com.example.mealplan.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.Flow

class MealRepository (private val dao: MealDao) {
    suspend fun insertMeal(meal: Meal): Meal {
        return dao.insert(meal)
    }
    suspend fun removeMeal(meal: Meal) = dao.delete(meal)
    fun getAllMeals() = dao.getAllMeals()
    fun searchMealByDate(date: String): Flow<List<Meal>> {
        return dao.searchMealByDate(date)
    }
    fun getItems() = dao.getItems()
    suspend fun updateMeal(meal: Meal): Meal {
        return dao.update(meal)
    }
    suspend fun searchMealByDateAndName(date: String, name: String): Meal {
        return dao.searchMealByDateAndName(date, name)
    }
}
