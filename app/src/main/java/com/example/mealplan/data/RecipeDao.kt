package com.example.mealplan.data

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    @Transaction
    @Insert(onConflict = ABORT)
    suspend fun insert(recipe: Recipe)

    @Delete
    suspend fun delete(recipe: Recipe)

    @Query("SELECT * FROM Recipe")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM Recipe WHERE name LIKE :name") //could improve to find by more than name
    fun searchRecipesByName(name: String): Flow<List<Recipe>?>
}