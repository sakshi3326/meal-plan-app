package com.example.mealplan.ui

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mealplan.R
import com.example.mealplan.data.Meal

class MealFragment: Fragment(R.layout.meal_fragment) {
    lateinit var meal: Meal

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)

        val args: MealFragmentArgs by navArgs()
        meal = args.meal

        val name: TextView = view.findViewById(R.id.meal_name)
        name.text = meal.name

        val save_btn: Button = view.findViewById(R.id.save_meal_btn)
        save_btn.setOnClickListener{
            saveAndExit()
        }

        val ingredients_btn: Button = view.findViewById(R.id.ingredients_add_btn)
        ingredients_btn.setOnClickListener {
            // need to change the passed parameter to represent the current form
            val directions = MealFragmentDirections.navigateFromMealFormToIngredientsSelection(meal, null)
            findNavController().navigate(directions)
        }
    }

    fun saveAndExit() {
        // save to the meal table
        saveMeal()
        findNavController().navigateUp()
    }

    // saves the current form fields into the meal table
    fun saveMeal() {
        null
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.meal_activity, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_recipe_item -> {
                //store the current meal into the RECIPE database
                saveAndExit() //then save to the meal and up navigate
                true
            }
            R.id.select_recipe_item -> {
                val directions = MealFragmentDirections.navigateFromMealFormToRecipeSelection("meal", meal)
                findNavController().navigate(directions)
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

    override fun onPause() {
        super.onPause()
        Log.d("Pause: ", "MealActivity")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Start: ", "MealActivity")
    }
}