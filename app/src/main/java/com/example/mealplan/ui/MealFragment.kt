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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mealplan.R
import com.example.mealplan.data.Meal
import com.example.mealplan.data.Recipe

class MealFragment: Fragment(R.layout.meal_fragment) {
    lateinit var meal: Meal
    lateinit var name: TextView
    lateinit var desc: TextView
    lateinit var notes: TextView
    lateinit var url: TextView

    private val recipeViewModel: RecipeViewModel by viewModels()
    private val mealViewModel: MealViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        val args: MealFragmentArgs by navArgs()
        meal = args.meal

        name = view.findViewById(R.id.meal_name)
        name.text = meal.name
        desc = view.findViewById(R.id.meal_description_text)
        desc.text = meal.description
        notes = view.findViewById(R.id.meal_notes_text)
        notes.text = meal.notes
        url = view.findViewById(R.id.meal_url_text)
        url.text = meal.url

        val save_btn: Button = view.findViewById(R.id.save_meal_btn)
        save_btn.setOnClickListener{
            saveAndExit()
        }

        val ingredients_btn: Button = view.findViewById(R.id.ingredients_add_btn)
        ingredients_btn.setOnClickListener {
            meal.description = desc.text.toString()
            meal.notes = notes.text.toString()
            meal.url = url.text.toString()
            mealViewModel.updateMeal(meal)
            mealViewModel.meal.observe(viewLifecycleOwner) { thisMeal ->
                mealViewModel.meal.removeObservers(viewLifecycleOwner)
                val directions = MealFragmentDirections.navigateFromMealFormToIngredientsSelection(thisMeal, null)
                findNavController().navigate(directions)
            }

        }
    }

    fun saveAndExit() {
        // save to the meal table
        saveMeal()
        findNavController().navigateUp()
    }

    // saves the current form fields into the meal table
    fun saveMeal() {
        meal.description = desc.text.toString()
        meal.notes = notes.text.toString()
        meal.url = url.text.toString()
        mealViewModel.updateMeal(meal)
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.meal_activity, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.save_recipe_item -> {
                //need to update the foodItems for this meal to point to the recipe also
                // in other words, we need to adjust the recipe_id field for each foodItem such
                // that they point to the recipe created by the following line:
                recipeViewModel.addRecipe(Recipe(description = desc.text.toString(), notes = notes.text.toString(), url = url.text.toString()))
                saveAndExit()
                true
            }
            R.id.select_recipe_item -> {
                meal.description = desc.text.toString()
                meal.notes = notes.text.toString()
                meal.url = url.text.toString()
                mealViewModel.updateMeal(meal)
                val directions = MealFragmentDirections.navigateFromMealFormToRecipeSelection("meal", meal)
                findNavController().navigate(directions)
                true
            }
            else -> {
                saveMeal()
                super.onOptionsItemSelected(item)
            }
        }
    }


    //for debugging purposes in the lifecycle model

    override fun onResume() {
        mealViewModel.getMealByDateAndName(meal.date, meal.name)
        mealViewModel.meal.observe(viewLifecycleOwner) { thisMeal ->
            meal = thisMeal
            desc.text = meal.description
            notes.text = meal.notes
            url.text = meal.url
            // need to update the ingredients here to display - data should be updated
            // in the recipe selection fragment on the click listener "onRecipeClick"
        }
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