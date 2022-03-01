package com.example.mealplan.ui

import android.content.Intent
import android.content.Intent.*
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.mealplan.R

class MealActivity: AppCompatActivity() {
    lateinit var meal_name: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.meal_activity)
        Log.d("Create: ", "MealActivity")

        meal_name = getIntent().extras?.get("name").toString()

        val name: TextView = findViewById(R.id.meal_name)
        name.text = meal_name

        val save_btn: Button = findViewById(R.id.save_meal_btn)
        save_btn.setOnClickListener{
            saveAndExit()
        }

        val ingredients_btn: Button = findViewById(R.id.ingredients_add_btn)
        ingredients_btn.setOnClickListener {
            //val intent = Intent(this, IngredientsSelection::class.java)
            //startActivity(intent)
        }
    }

    fun saveAndExit() {
        // save to the meal table
        saveMeal()
        // navigate up
        val intent = Intent(this, MealSelection::class.java)
        intent.addFlags(FLAG_ACTIVITY_REORDER_TO_FRONT) // launch the existing task of MealSelection
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP)        // destroy this activity
        startActivity(intent)
    }

    // saves the current form fields into the meal table
    fun saveMeal() {
        null
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.meal_activity, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_recipe_item -> {
                //store the current meal into the RECIPE database
                saveAndExit() //then save to the meal and up navigate
                true
            }
            R.id.select_recipe_item -> {
                // intent for recipe selection menu button
                // val intent = Intent(this, RecipeSelection::class.java)
                // startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    //for debugging purposes in the lifecycle model

    override fun onResume() {
        super.onResume()
        Log.d("Resume: ", "MealActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Destroy: ", "MealActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Stop: ", "MealActivity")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d("Restart: ", "MealActivity")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Pause: ", "MealActivity")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Start: ", "MealActivity")
    }
}