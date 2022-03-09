package com.example.mealplan.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.*
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplan.R
import com.example.mealplan.data.Meal
import com.example.mealplan.data.Recipe

class RecipeSelectionFragment: Fragment(R.layout.recipe_selection_fragment) {

    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recipeListRV: RecyclerView
    private lateinit var origin: String
    private lateinit var meal: Meal

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val args: RecipeSelectionFragmentArgs by navArgs()
        origin = args.origin
        if (args.meal != null)
            meal = args.meal!!

        if (origin == "MainActivity") {
            val recipeAddBtn: Button = view.findViewById(R.id.add_recipe_btn)
            recipeAddBtn.visibility = VISIBLE
            recipeAddBtn.setOnClickListener{
                val directions = RecipeSelectionFragmentDirections.navigateFromRecipeSelectionToRecipeForm(null)
                findNavController().navigate(directions)
            }
        }

        recipeListRV = view.findViewById(R.id.recipes_rv)

        recipeAdapter = RecipeAdapter(::onRecipeClick)

        recipeListRV.layoutManager = LinearLayoutManager(requireContext())
        recipeListRV.setHasFixedSize(true)
        recipeListRV.adapter = recipeAdapter

        recipeAdapter.updateRecipes(Recipe("Recipe1", "Some notes", null))
        recipeAdapter.updateRecipes(Recipe("Recipe2", "Some notes", null))
        recipeAdapter.updateRecipes(Recipe("Recipe3", "Some notes", null))
        recipeAdapter.updateRecipes(Recipe("Recipe4", "Some notes", null))
        recipeAdapter.updateRecipes(Recipe("Recipe5", "Some notes", null))

        Log.d("Create: ", "Recipe Selection")
    }

    private fun onRecipeClick(recipe: Recipe) {
        if (origin != "MainActivity") {
            // will need to save the meal fields as the recipe clicked on before going up
            findNavController().navigateUp()
        }
        else {
            val directions = RecipeSelectionFragmentDirections.navigateFromRecipeSelectionToRecipeForm(recipe)
            findNavController().navigate(directions)
        }
    }

    override fun onResume() {
        super.onResume()
        Log.d("Resume: ", "Recipe Selection")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Destroy: ", "Recipe Selection")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Stop: ", "Recipe Selection")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Pause: ", "Recipe Selection")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Start: ", "Recipe Selection")
    }
}