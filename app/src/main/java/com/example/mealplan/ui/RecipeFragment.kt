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
import com.example.mealplan.data.NutrientData
import com.example.mealplan.data.Recipe

class RecipeFragment: Fragment(R.layout.recipe_fragment) {

    lateinit var recipeDesc: TextView
    lateinit var recipeNotes: TextView
    lateinit var recipeUrl: TextView

    lateinit var carbs: TextView
    lateinit var fat: TextView
    lateinit var cals: TextView
    lateinit var protein: TextView

    private val viewModel: RecipeViewModel by viewModels()
    private val nutrientsViewModel: NutrientsViewModel by viewModels()

    private var recipeIngredientsAdapter = RecipeIngredientsAdapter(::onFoodItemClick)
    private val recipeIngredientsViewModel: RecipeIngredientsViewModel by viewModels()
    private lateinit var recipeIngredientsRV: RecyclerView


    var recipe: Recipe? = null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeDesc = view.findViewById(R.id.recipe_description_text)
        recipeNotes = view.findViewById(R.id.recipe_notes_text)
        recipeUrl = view.findViewById(R.id.recipe_url_text)

        carbs = view.findViewById(R.id.carbs)
        fat = view.findViewById(R.id.fat)
        cals = view.findViewById(R.id.calories)
        protein = view.findViewById(R.id.protein)

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

        if (recipe?.id != null)
            nutrientsViewModel.searchAllNutrientsByRecipeId(recipe!!.id)
        nutrientsViewModel.nutrients.observe(viewLifecycleOwner) { nutrients ->
            computeNutritionalInfo(nutrients)
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

    fun computeNutritionalInfo(nutrients: List<NutrientData>?) {
        var proteinVal = 0.0
        var calorieVal = 0.0
        var carbsVal = 0.0
        var fatsVal = 0.0
        if (nutrients != null) {
            var proteins = nutrients.filter { nutrient -> "Protein".equals(nutrient.name) }
            for (protein in proteins) {
                if (protein.unit == "G") {
                    proteinVal += protein.nutritionVal
                } else {
                    proteinVal += protein.nutritionVal / 1000
                }
            }
            var calories = nutrients.filter { nutrient -> "Energy".equals(nutrient.name) }
            for (calorie in calories) {
                if (calorie.unit == "KCAL") {
                    calorieVal += calorie.nutritionVal
                }
            }
            var carbs = nutrients.filter { nutrient -> "Carbohydrate, by difference".equals(nutrient.name) }
            for (carb in carbs) {
                if (carb.unit == "G") {
                    carbsVal += carb.nutritionVal
                }
                else {
                    carbsVal += carb.nutritionVal / 1000
                }
            }
            var fats = nutrients.filter { nutrient -> nutrient.name.equals("Total lipid (fat)") }
            for (fat in fats) {
                if (fat.unit == "G") {
                    fatsVal += fat.nutritionVal
                }
                else {
                    fatsVal += fat.nutritionVal / 1000
                }
            }
        }
        carbs.text = "Carbohydrates: " + String.format("%.2f", carbsVal) + " G"
        fat.text = "Fat: " + String.format("%.2f", fatsVal) + " G"
        cals.text = "Calories: " + String.format("%.2f", calorieVal) + " Calories"
        protein.text = "Protein: " + String.format("%.2f", proteinVal) + " G"
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