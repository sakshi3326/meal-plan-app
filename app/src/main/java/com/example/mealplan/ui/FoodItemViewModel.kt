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
    val foodItems = MutableLiveData<List<FoodItemData>?>()

    fun addItem(item: FoodItemData) {
        viewModelScope.launch {
            foodItem.postValue(repository.insertItem(item))
        }
    }

    fun updateItem(item: FoodItemData) {
        viewModelScope.launch {
            repository.updateItem(item)
        }
    }

    fun removeItem(item: FoodItemData) {
        viewModelScope.launch {
            repository.deleteItem(item)
        }
    }

    fun searchAllItemsByMealId(id: Long) {
        viewModelScope.launch {
            foodItems.postValue(repository.searchItemsByMealID(id))
        }
    }



}