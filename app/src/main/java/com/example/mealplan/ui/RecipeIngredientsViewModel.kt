package com.example.mealplan.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.mealplan.data.*
import kotlinx.coroutines.launch

class RecipeIngredientsViewModel(application: Application) : AndroidViewModel(application){
	//private val repository = FoodItemsListRepository(MealPlanService.create())
	private val repository = FoodItemRepository(
		AppDatabase.getInstance(application).FoodItemDao()
	)
	var recipeIngredients: LiveData<List<FoodItemData>?> = repository.searchItemsByRecipeID(0).asLiveData()

	fun showIngredients(recipeID: Long) {
		viewModelScope.launch {
			recipeIngredients = repository.searchItemsByRecipeID(recipeID).asLiveData()
			}
		}
	}