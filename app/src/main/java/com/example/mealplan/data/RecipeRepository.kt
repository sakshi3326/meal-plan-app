package com.example.mealplan.data

class RecipeRepository (private val dao: RecipeDao) {
    suspend fun insertRecipe(recipe: Recipe) = dao.insert(recipe)
    suspend fun removeRecipe(recipe: Recipe) = dao.delete(recipe)
    fun getAllRecipes() = dao.getAllRecipes()
    fun searchRecipesByName(name: String) = dao.searchRecipesByName(name)
}