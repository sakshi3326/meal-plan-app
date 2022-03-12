package com.example.mealplan.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mealplan.data.AppDatabase
import com.example.mealplan.data.Meal
import com.example.mealplan.data.MealRepository
import kotlinx.coroutines.launch

class MealViewModel(application: Application): AndroidViewModel(application) {
    private val repository = MealRepository(
        AppDatabase.getInstance(application).MealDao()
    )

    var meals: LiveData<List<Meal>?> = repository.getAllMeals().asLiveData()

    fun addMeal(meal: Meal) {
        viewModelScope.launch {
            repository.insertMeal(meal)
        }
    }

    fun removeMeal(meal: Meal) {
        viewModelScope.launch {
            repository.removeMeal(meal)
        }
    }

    fun getMealByDate(date: String) {
        viewModelScope.launch {
            val results = repository.searchMealByDate(date).asLiveData()
            if (results.value != null)
                meals = results
            else
                addMeal(Meal("Breakfast", date))
                addMeal(Meal("Lunch", date))
                addMeal(Meal("Dinner", date))
                meals = repository.searchMealByDate(date).asLiveData()
        }
    }
}