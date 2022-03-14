package com.example.mealplan.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplan.R
import com.example.mealplan.data.FoodItemData
import com.example.mealplan.data.Recipe

class RecipeFragment: Fragment(R.layout.recipe_fragment) {

    lateinit var recipeDesc: TextView
    lateinit var recipeNotes: TextView
    lateinit var recipeUrl: TextView


    private val viewModel: RecipeViewModel by viewModels()

    private var recipeIngredientsAdapter = RecipeIngredientsAdapter(::onFoodItemClick)
    private val recipeIngredientsViewModel: RecipeIngredientsViewModel by viewModels()
    private lateinit var recipeIngredientsRV: RecyclerView


    var recipe: Recipe? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        recipeDesc = view.findViewById(R.id.recipe_description_text)
        recipeNotes = view.findViewById(R.id.recipe_notes_text)
        recipeUrl = view.findViewById(R.id.recipe_url_text)
        //Recipe Adapter
        recipeIngredientsRV = view.findViewById(R.id.rv_recipe_ingredients)

        recipeIngredientsRV.layoutManager = LinearLayoutManager(requireContext())
        recipeIngredientsRV.setHasFixedSize(true)

        recipeIngredientsRV.adapter = recipeIngredientsAdapter
        val args: RecipeFragmentArgs by navArgs()
        if (args.recipe != null) {
            recipe = args.recipe
            recipeDesc.text = recipe!!.description
            recipeNotes.text = recipe?.notes.toString()
            recipeUrl.text = recipe?.url.toString()
            recipeIngredientsViewModel.showIngredients(recipe!!.id)
        }

        recipeIngredientsViewModel.recipeIngredients.observe(viewLifecycleOwner) { foodItem ->
            recipeIngredientsAdapter.showIngredients(foodItem)
        }

        val save_btn: Button = view.findViewById(R.id.save_recipe_btn)
        save_btn.setOnClickListener{
            saveAndExit()
        }

        val ingredients_btn: Button = view.findViewById(R.id.recipe_ingredients_add_btn)
        ingredients_btn.setOnClickListener {
            // need to change the passed parameter to represent the current form
            if (recipe == null) {
                recipe = Recipe(
                    description = recipeDesc.text.toString(),
                    notes = recipeNotes.text.toString(),
                    url = recipeUrl.text.toString()
                )
                viewModel.addRecipe(recipe!!)
            }
            else {
                recipe?.description = recipeDesc.text.toString()
                recipe?.notes = recipeNotes.text.toString()
                recipe?.url = recipeUrl.text.toString()
                viewModel.updateRecipe(recipe!!)
            }
            viewModel.recipe.observe(viewLifecycleOwner) { thisRecipe ->
                viewModel.recipe.removeObservers(viewLifecycleOwner)
                val directions = RecipeFragmentDirections.navigateFromRecipeFormToIngredientsSelection(null, thisRecipe)
                findNavController().navigate(directions)
            }

        }
    }

    private fun onFoodItemClick(foodItem: FoodItemData) {
        // save either the recipe or meal passed into the args with this ingredient added
        //findNavController().navigateUp()
        //TODO(Whatever this does)
    }

    private fun saveAndExit() {
        // save to the recipe table
        saveRecipe()
        // navigate up
        findNavController().navigateUp()
    }

    private fun saveRecipe() {
        if (recipe == null) {
            var r = Recipe(
                description = recipeDesc.text.toString(),
                notes = recipeNotes.text.toString(),
                url = recipeUrl.text.toString()
            )
            viewModel.addRecipe(r)
        }
        else {
            recipe?.description = recipeDesc.text.toString()
            recipe?.notes = recipeNotes.text.toString()
            recipe?.url = recipeUrl.text.toString()
            viewModel.updateRecipe(recipe!!)
        }
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