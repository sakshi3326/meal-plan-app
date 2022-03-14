package com.example.mealplan.data

class RecipeRepository (private val dao: RecipeDao) {
    suspend fun insertRecipe(recipe: Recipe): Recipe {
        return dao.insert(recipe)
    }
    suspend fun removeRecipe(recipe: Recipe) = dao.delete(recipe)
    fun getAllRecipes() = dao.getAllRecipes()
    fun searchRecipesByName(name: String) = dao.searchRecipesByName(name)
    fun getItems() = dao.getItems()
    suspend fun updateRecipe(update: Recipe): Recipe {
        return dao.update(update)
    }
}