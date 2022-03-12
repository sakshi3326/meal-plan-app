package com.example.mealplan.data

class MealRepository (private val dao: MealDao) {
    suspend fun insertMeal(meal: Meal) = dao.insert(meal)
    suspend fun removeMeal(meal: Meal) = dao.delete(meal)
    fun getAllMeals() = dao.getAllMeals()
    fun searchMealByDate(date: String) = dao.searchMealByDate(date)
}
