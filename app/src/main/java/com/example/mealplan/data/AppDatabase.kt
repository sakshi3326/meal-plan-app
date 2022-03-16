package com.example.mealplan.data

import android.content.Context
import androidx.room.*

const val DATABASE_NAME = "mealplan-db"

@Database(
    entities = [Recipe::class, Meal::class, FoodItemData::class, NutrientData::class],
    version = 1)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase(){
    abstract fun RecipeDao(): RecipeDao
    abstract fun MealDao(): MealDao
    abstract fun FoodItemDao(): FoodItemDao
    abstract fun NutrientDao(): NutrientDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

        // for now, the database will clear itself when the version is changed - for easy testing
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build()
    }
}