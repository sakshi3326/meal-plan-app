package com.example.mealplan.data

class FoodItemRepository (private val dao: FoodItemDao){
    suspend fun insertItem(foodItemData: FoodItemData) = dao.insert(foodItemData)
    suspend fun deleteItem(foodItemData: FoodItemData) = dao.delete(foodItemData)
    fun getAllItems() = dao.getAllItems()
    fun searchItemsByName(name: String) = dao.searchItemsByName(name)
    fun getItemNutrients() = dao.getItemNutrients()
}