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
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplan.R
import com.example.mealplan.data.*
import kotlinx.coroutines.delay
import java.util.*
import java.util.Timer
import kotlin.concurrent.schedule

class MealFragment: Fragment(R.layout.meal_fragment) {
    lateinit var meal: Meal
    lateinit var name: TextView
    lateinit var desc: TextView
    lateinit var notes: TextView
    lateinit var url: TextView

    private val recipeViewModel: RecipeViewModel by viewModels()
    private val mealViewModel: MealViewModel by viewModels()
    private val nutrientsViewModel: NutrientsViewModel by viewModels()

    private var proteinVal = MutableLiveData<Double>()
    private var calVal = MutableLiveData<Double>()
    private var fatVal = MutableLiveData<Double>()

    //NEW Adapter
    private var mealIngredientsAdapter = MealIngredientsAdapter(::onFoodItemClick)
    private val mealIngredientsViewModel: MealIngredientsViewModel by viewModels()
    private lateinit var mealIngredientsRV: RecyclerView

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

        proteinVal.postValue(0.0)
        fatVal.postValue(0.0)
        calVal.postValue(0.0)

        //NEW Adapter
        mealIngredientsRV = view.findViewById(R.id.rv_meal_ingredients)

        mealIngredientsRV.layoutManager = LinearLayoutManager(requireContext())
        mealIngredientsRV.setHasFixedSize(true)

        mealIngredientsRV.adapter = mealIngredientsAdapter

        mealIngredientsViewModel.showIngredients(meal.id)


        mealIngredientsViewModel.mealIngredients.observe(viewLifecycleOwner) { foodItem ->
            mealIngredientsAdapter.showIngredients(foodItem)
        }

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


    private fun onFoodItemClick(foodItem: FoodItemData) {
        // save either the recipe or meal passed into the args with this ingredient added
        //findNavController().navigateUp()
        //TODO(Whatever this does)
    }

    fun computeNutritionalInfo(nutrients: List<NutrientData>?, view: View) {
//        Log.d("nutrients: ", nutrients.toString())
//        if (nutrients != null) {
//            for (nutrient in nutrients) {
//                if (nutrient != null) {
//                    Log.d("nutrient: ", nutrient.toString())
//                    var protein = nutrient?.find{ nutrient -> "Protein".equals(nutrient.name)}
//                    if (protein?.unit == "G") {
//                        proteinVal.value = proteinVal.value?.plus(protein.nutritionVal)
//                    }
//                    else {
//                        if (protein != null) {
//                            proteinVal.value = proteinVal.value?.plus(protein.nutritionVal / 1000)
//                        }
//                    }
//                }
//
//            }
//        }
//        var textView: TextView = view.findViewById(R.id.carbs)
//        textView.text = proteinVal.value.toString()
//        Log.d("protein: ", proteinVal.value.toString())
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
                //TODO need to update the foodItems for this meal to point to the recipe also
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
            //TODO need to update the ingredients here to display - data should be updated
            // in the recipe selection fragment on the click listener "onRecipeClick"
        }
        nutrientsViewModel.searchAllNutrientsByMealId(meal.id)
        nutrientsViewModel.nutrients.observe(viewLifecycleOwner) { nutrients ->
            Log.d("NutrientsInResume: ", nutrients.toString())
            //TODO call the compute function on the nutrients list
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