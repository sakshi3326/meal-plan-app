package com.example.mealplan.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealplan.api.MealPlanService
import com.example.mealplan.data.*
import kotlinx.coroutines.launch

class FoodItemsListViewModel : ViewModel(){
    private val listRepository = FoodItemsListRepository(MealPlanService.create())

    private val _foodItems = MutableLiveData<FoodItemsList?>(null)
    val foodItems: LiveData<FoodItemsList?> = _foodItems

    private val _loadingStatus = MutableLiveData(LoadingStatus.SUCCESS)
    val loadingStatus: LiveData<LoadingStatus> = _loadingStatus

    fun searchIngredients(query: String?) {
        viewModelScope.launch {
            _loadingStatus.value = LoadingStatus.LOADING
            val result = listRepository.searchIngredientsList(query)
            _foodItems.value = result.getOrNull()
            _loadingStatus.value = when (result.isSuccess) {
                true -> LoadingStatus.SUCCESS
                false -> LoadingStatus.ERROR
            }
        }
    }


}