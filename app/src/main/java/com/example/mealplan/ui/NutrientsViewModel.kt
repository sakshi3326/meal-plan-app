package com.example.mealplan.ui

import android.app.Application
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

}