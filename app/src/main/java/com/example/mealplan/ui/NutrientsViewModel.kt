package com.example.mealplan.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mealplan.data.*
import kotlinx.coroutines.launch

class NutrientsViewModel(application: Application): AndroidViewModel(application) {
    private val repository = NutrientRepository(
        AppDatabase.getInstance(application).NutrientDao()
    )

    val nutrient = MutableLiveData<NutrientData>()
    val nutrients = MutableLiveData<List<NutrientData>?>()

    fun addNutrient(nutrientD: NutrientData) {
        viewModelScope.launch {
            nutrient.postValue(repository.insertNutrient(nutrientD))
        }
    }

    fun removeItem(nutrient: NutrientData) {
        viewModelScope.launch {
            repository.removeNutrient(nutrient)
        }
    }

    fun searchNutrientsById(id: Long): NutrientData? {
        var results: NutrientData? = null
        viewModelScope.launch {
            results = repository.searchNutrientById(id).asLiveData().value
        }
        return results
    }
    fun deleteNutrientById(id: Long) {
        viewModelScope.launch {
            repository.deleteNutrientById(id)
        }
    }

    fun searchAllNutrientsByMealId(id: Long) {
        viewModelScope.launch{
            nutrients.postValue(repository.searchAllNutrientsForMeal(id))
        }
    }

}