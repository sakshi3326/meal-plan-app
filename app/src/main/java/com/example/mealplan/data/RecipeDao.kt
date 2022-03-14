package com.example.mealplan.data

import androidx.room.*
import androidx.room.OnConflictStrategy.ABORT
import androidx.room.OnConflictStrategy.REPLACE
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {
    suspend fun insert(recipe: Recipe): Recipe {
        recipe.id = _insert(recipe)
        return recipe
    }

    suspend fun update(recipe: Recipe): Recipe {
        _update(recipe)
        return recipe
    }

    @Transaction
    @Insert(onConflict = REPLACE)
    suspend fun _insert(recipe: Recipe): Long

    @Update
    suspend fun _update(recipe: Recipe)

    @Delete
    suspend fun delete(recipe: Recipe)

    @Query("SELECT * FROM Recipe")
    fun getAllRecipes(): Flow<List<Recipe>>

    @Query("SELECT * FROM Recipe WHERE description LIKE :description") //could improve to find by more than name
    fun searchRecipesByName(description: String): Flow<List<Recipe>?>

    @Transaction
    @Query("SELECT * FROM Recipe")
    fun getItems(): List<RecipeWithItems>
}