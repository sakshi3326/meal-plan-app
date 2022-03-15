package com.example.mealplan.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.mealplan.api.MealPlanService
import com.example.mealplan.data.*
import kotlinx.coroutines.launch

class MealIngredientsViewModel(application: Application) : AndroidViewModel(application){
	//private val repository = FoodItemsListRepository(MealPlanService.create())
	private val repository = FoodItemRepository(
		AppDatabase.getInstance(application).FoodItemDao()
	)
	var mealIngredients = MutableLiveData<List<FoodItemData>>()
	var mealIngredient = MutableLiveData<FoodItemData>()


	fun showIngredients(mealID: Long) {
		viewModelScope.launch {
			mealIngredients.postValue(repository.searchItemsByMealID(mealID))
			}
		}

	}