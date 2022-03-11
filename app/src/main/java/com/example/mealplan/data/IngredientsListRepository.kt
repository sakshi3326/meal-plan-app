package com.example.mealplan.data

import android.util.Log
import com.example.mealplan.api.MealPlanService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class IngredientsListRepository(
    private val service: MealPlanService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
    ) {
    suspend fun searchIngredientsList(query: String?) : Result<IngredientsList> {
        return withContext(ioDispatcher) {
            try {
                val ingredients = service.searchIngredients(query)
                Result.success(ingredients)
            } catch (e: Exception) {
                Log.d("ERROR: ", e.toString())
                Result.failure(e)
            }
        }
    }
}