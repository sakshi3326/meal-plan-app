package com.example.mealplan.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.mealplan.data.*
import kotlinx.coroutines.launch


class RecipeViewModel(application: Application): AndroidViewModel(application) {
    private val repository = RecipeRepository(
        AppDatabase.getInstance(application).RecipeDao()
    )

    var recipes: LiveData<List<Recipe>?> = repository.getAllRecipes().asLiveData()

    fun addRecipe(recipe: Recipe) {
        viewModelScope.launch {
            repository.insertRecipe(recipe)
        }
    }

    fun removeRecipe(recipe: Recipe) {
        viewModelScope.launch {
            repository.removeRecipe(recipe)
        }
    }

    fun searchRecipes(name: String) {
        viewModelScope.launch {
            recipes = repository.searchRecipesByName(name).asLiveData()
        }
    }
}