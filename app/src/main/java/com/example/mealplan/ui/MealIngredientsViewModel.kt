package com.example.mealplan.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealplan.api.MealPlanService
import com.example.mealplan.data.FoodItemsList
import com.example.mealplan.data.FoodItemsListRepository
import com.example.mealplan.data.LoadingStatus
import kotlinx.coroutines.launch

class MealIngredientsViewModel : ViewModel(){
	//private val repository = FoodItemsListRepository(MealPlanService.create())

	private val _mealIngredients = MutableLiveData<FoodItemsList?>(null)
	val mealIngredients: LiveData<FoodItemsList?> = _mealIngredients

	private val _loadingStatus = MutableLiveData(LoadingStatus.SUCCESS)
	val loadingStatus: LiveData<LoadingStatus> = _loadingStatus

	/*fun searchIngredients(query: String?) {
		viewModelScope.launch {
			_loadingStatus.value = LoadingStatus.LOADING
			val result = repository.searchIngredientsList(query)
			_foodItems.value = result.getOrNull()
			_loadingStatus.value = when (result.isSuccess) {
				true -> LoadingStatus.SUCCESS
				false -> LoadingStatus.ERROR
			}
		}
	}*/
}