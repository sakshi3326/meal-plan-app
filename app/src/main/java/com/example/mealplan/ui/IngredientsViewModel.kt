package com.example.mealplan.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealplan.api.MealPlanService
import com.example.mealplan.data.IngredientsList
import com.example.mealplan.data.IngredientsListRepository
import com.example.mealplan.data.LoadingStatus
import kotlinx.coroutines.launch

class IngredientsViewModel : ViewModel(){
    private val repository = IngredientsListRepository(MealPlanService.create())

    private val _ingredients = MutableLiveData<IngredientsList?>(null)
    val ingredients: LiveData<IngredientsList?> = _ingredients

    private val _loadingStatus = MutableLiveData(LoadingStatus.SUCCESS)
    val loadingStatus: LiveData<LoadingStatus> = _loadingStatus

    fun searchIngredients(query: String?) {
        viewModelScope.launch {
            _loadingStatus.value = LoadingStatus.LOADING
            val result = repository.searchIngredientsList(query)
            _ingredients.value = result.getOrNull()
            _loadingStatus.value = when (result.isSuccess) {
                true -> LoadingStatus.SUCCESS
                false -> LoadingStatus.ERROR
            }
        }
    }
}