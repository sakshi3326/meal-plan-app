package com.example.mealplan.data

import android.util.Log
import com.example.mealplan.api.MealPlanService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class FoodItemsListRepository(
    private val service: MealPlanService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
    suspend fun searchIngredientsList(query: String?) : Result<FoodItemsList> {
        return withContext(ioDispatcher) {
            try {
                val foodItems = service.searchIngredients(query)
                Result.success(foodItems)
            } catch (e: Exception) {
                Log.d("ERROR: ", e.toString())
                Result.failure(e)
            }
        }
    }
}