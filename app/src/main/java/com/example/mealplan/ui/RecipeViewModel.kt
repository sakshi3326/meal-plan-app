package com.example.mealplan.ui

import android.app.Application
import androidx.lifecycle.*
import com.example.mealplan.data.*
import kotlinx.coroutines.launch


class RecipeViewModel(application: Application): AndroidViewModel(application) {
    private val repository = RecipeRepository(
        AppDatabase.getInstance(application).RecipeDao()
    )

    var recipes: LiveData<List<Recipe>?> = repository.getAllRecipes().asLiveData()
    var recipe = MutableLiveData<Recipe>()

    fun addRecipe(addRecipe: Recipe) {
        viewModelScope.launch {
            recipe.postValue(repository.insertRecipe(addRecipe))
        }
    }

    fun updateRecipe(updateRecipe: Recipe) {
        viewModelScope.launch {
            recipe.postValue(repository.updateRecipe(updateRecipe))
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