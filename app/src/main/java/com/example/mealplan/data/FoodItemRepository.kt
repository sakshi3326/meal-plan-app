package com.example.mealplan.data

class FoodItemRepository (private val dao: FoodItemDao){
    suspend fun insertItem(foodItemData: FoodItemData): FoodItemData {
        val item: FoodItemData = dao.insert(foodItemData)
        return item
    }
    suspend fun updateItem(item: FoodItemData) = dao.update(item)
    suspend fun deleteItem(foodItemData: FoodItemData) = dao.delete(foodItemData)
    fun getAllItems() = dao.getAllItems()
    fun searchItemsByName(name: String) = dao.searchItemsByName(name)
    fun searchItemsByMealID(mealID: Long): List<FoodItemData>? {
        return dao.searchItemsByMealID(mealID)
    }
    fun searchItemsByRecipeID(recipeID: Long) = dao.searchItemsByRecipeID(recipeID)
    fun getItemNutrients() = dao.getItemNutrients()
}