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

    fun searchNutrientbyItemId(id: Long) {
//        var query: List<NutrientData>? = null
        viewModelScope.launch {
//            var temp = nutrients.value
//            var temp2 = repository.searchNutrientByItemId(id)
////            query = repository.searchNutrientByItemId(id)
//            temp?.add(repository.searchNutrientByItemId(id))
//            Log.d("temp: ", temp.toString())

            nutrients.postValue(listOf<NutrientData>()?.plus(nutrients.value).plus(repository.searchNutrientByItemId(id)) as List<NutrientData>?)


//            Log.d("query", query.toString())


//            Log.d("nutrient.value: ", nutrients.value.toString())
////
//            if (temp != null){
//                Log.d("temp not null", "")
//                nutrients.postValue(temp!!)
//            }
//            Log.d("nutrient.value: ", nutrients.value.toString())
//            nutrients.value?.add(repository.searchNutrientByItemId(id).asLiveData().value)

        }

////
//        Log.d("temp: ", temp.toString())
    }

}