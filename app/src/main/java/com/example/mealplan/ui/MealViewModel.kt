package com.example.mealplan.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.mealplan.data.AppDatabase
import com.example.mealplan.data.Meal
import com.example.mealplan.data.MealRepository
import kotlinx.coroutines.launch

class MealViewModel(application: Application): AndroidViewModel(application) {
    private val repository = MealRepository(
        AppDatabase.getInstance(application).MealDao()
    )

    var meals: LiveData<List<Meal>> = repository.getAllMeals().asLiveData()
    var meal = MutableLiveData<Meal>()

    fun addMeal(addMeal: Meal) {
        viewModelScope.launch {
            meal.postValue(repository.insertMeal(addMeal))
        }
    }

    fun updateMeal(updateMeal: Meal) {
        viewModelScope.launch {
            meal.postValue(repository.updateMeal(updateMeal))
        }
    }

    fun removeMeal(meal: Meal) {
        viewModelScope.launch {
            repository.removeMeal(meal)
        }
    }

    fun getMealByDate(date: String) {
        viewModelScope.launch {
            meals = repository.searchMealByDate(date).asLiveData()
        }
    }

    fun getMealByDateAndName(date: String, name: String) {
        viewModelScope.launch {
            meal.postValue(repository.searchMealByDateAndName(date, name))
        }
    }

}