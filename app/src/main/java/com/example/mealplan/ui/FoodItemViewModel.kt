package com.example.mealplan.ui

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.mealplan.data.*
import kotlinx.coroutines.launch

class FoodItemViewModel(application: Application): AndroidViewModel(application) {
    private val repository = FoodItemRepository(
        AppDatabase.getInstance(application).FoodItemDao()
    )

    val foodItem = MutableLiveData<FoodItemData>()

    fun addItem(item: FoodItemData) {
        viewModelScope.launch {
            foodItem.postValue(repository.insertItem(item))
        }
    }

    fun removeItem(item: FoodItemData) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    fun searchItemsByName(name: String): FoodItemData? {
        var results: FoodItemData? = null
        viewModelScope.launch {
            results = repository.searchItemsByName(name).asLiveData().value
        }
        return results
    }

    fun getItemNutrients(): List<FoodItemDataWithNutrients>? {
        var results: List<FoodItemDataWithNutrients>? = null
        viewModelScope.launch {
            results = repository.getItemNutrients()
        }
        return results
    }

}