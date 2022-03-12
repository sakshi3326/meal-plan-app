package com.example.mealplan.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.mealplan.R
import com.example.mealplan.data.Recipe

class RecipeFragment: Fragment(R.layout.recipe_fragment) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val save_btn: Button = view.findViewById(R.id.save_recipe_btn)
        save_btn.setOnClickListener{
            saveAndExit()
        }

        val ingredients_btn: Button = view.findViewById(R.id.recipe_ingredients_add_btn)
        ingredients_btn.setOnClickListener {
            // need to change the passed parameter to represent the current form
            val directions = RecipeFragmentDirections.navigateFromRecipeFormToIngredientsSelection(null, Recipe("My Recipe", null, null))
            findNavController().navigate(directions)
        }
    }

    private fun saveAndExit() {
        // save to the recipe table
        saveRecipe()
        // navigate up
        findNavController().navigateUp()
    }

    private fun saveRecipe() {
        null
    }


    //for debugging purposes in the lifecycle model

    override fun onResume() {
        super.onResume()
        Log.d("Resume: ", "RecipeActivity")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Destroy: ", "RecipeActivity")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Stop: ", "RecipeActivity")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Pause: ", "RecipeActivity")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Start: ", "RecipeActivity")
    }
}