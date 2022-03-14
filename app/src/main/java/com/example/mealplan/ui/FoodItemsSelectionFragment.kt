package com.example.mealplan.ui

import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplan.R
import com.example.mealplan.data.*
import com.google.android.material.progressindicator.CircularProgressIndicator

class FoodItemsSelectionFragment : Fragment(R.layout.food_item_selection_fragment) {
    private var foodItemsAdapter = FoodItemsAdapter()
    private val itemsViewModel: FoodItemsListViewModel by viewModels()
    private val nutrientsViewModel: NutrientsViewModel by viewModels()
    private val foodItemViewModel: FoodItemViewModel by viewModels()

    private lateinit var foodItemListRV: RecyclerView
    private lateinit var loadingIndicator: CircularProgressIndicator
    private lateinit var errorText : TextView

    private var recipe_id: Long? = null
    private var meal_id: Long? = null
    private lateinit var source: String

    private var items: MutableList<FoodItem> = mutableListOf()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        loadingIndicator = view.findViewById(R.id.loading_indicator)
        errorText = view.findViewById(R.id.tv_search_error)
        foodItemListRV = view.findViewById(R.id.rv_food_items)

        foodItemListRV.layoutManager = LinearLayoutManager(requireContext())
        foodItemListRV.setHasFixedSize(true)

        foodItemListRV.adapter = foodItemsAdapter

        val args: FoodItemsSelectionFragmentArgs by navArgs()
        if (args.recipe != null) {
            recipe_id = args.recipe!!.id
            source = "Recipe"
        }
        else {
            meal_id = args.meal!!.id
            source = "Meal"
        }

        itemsViewModel.foodItems.observe(viewLifecycleOwner) { foodItem ->
            foodItemsAdapter.searchIngredients(foodItem)
        }

        itemsViewModel.loadingStatus.observe(viewLifecycleOwner) { uiState ->
            when(uiState) {
                LoadingStatus.LOADING -> {
                    loadingIndicator.visibility = View.VISIBLE
                    foodItemListRV.visibility = View.INVISIBLE
                    errorText.visibility = View.INVISIBLE
                }
                LoadingStatus.ERROR -> {
                    loadingIndicator.visibility = View.INVISIBLE
                    foodItemListRV.visibility = View.INVISIBLE
                    errorText.visibility = View.VISIBLE
                }
                else -> {
                    loadingIndicator.visibility = View.INVISIBLE
                    foodItemListRV.visibility = View.VISIBLE
                    errorText.visibility = View.INVISIBLE
                }
            }
        }

        val searchBtn: Button = view.findViewById(R.id.food_item_search_btn)
        searchBtn.setOnClickListener {
            val searchTxt: TextView = view.findViewById(R.id.food_item_search_text)
            val query = searchTxt.text.toString()
            itemsViewModel.searchIngredients(query)
            foodItemListRV.scrollToPosition(0)
        }

        val clearBtn: Button = view.findViewById(R.id.ingredient_clear_btn)
        clearBtn.setOnClickListener {
            val searchTxt: TextView = view.findViewById(R.id.food_item_search_text)
            searchTxt.text = ""
        }

        val addBtn: Button = view.findViewById(R.id.add_food_items_btn)
        addBtn.setOnClickListener {
            val foodItems: MutableList<FoodItem> = foodItemsAdapter.selectedItems
            for (item in foodItems) {
                var foodItem: FoodItemData? = null
                foodItem = if (source == "Recipe")
                    FoodItemData(recipe_id = recipe_id, name = item.name, brandOwner = item.brandOwner, brand = item.brand, ingredients = item.ingredients, serving = item.serving, category = item.category, packaged = item.packaged, sizeUnit = item.sizeUnit, servingSize = item.servingSize)
                else
                    FoodItemData(meal_id = meal_id, name = item.name, brandOwner = item.brandOwner, brand = item.brand, ingredients = item.ingredients, serving = item.serving, category = item.category, packaged = item.packaged, sizeUnit = item.sizeUnit, servingSize = item.servingSize)
                foodItemViewModel.addItem(foodItem)
                foodItemViewModel.foodItem.observe(
                    viewLifecycleOwner,
                    object : Observer<FoodItemData> {
                        override fun onChanged(t: FoodItemData?) {
                            val id = t!!.id
                            for (nutrient in item.nutrients) {
                                nutrientsViewModel.addNutrient(
                                    NutrientData(
                                        item_id = id,
                                        name = nutrient.name,
                                        unit = nutrient.unit,
                                        nutritionVal = nutrient.nutritionVal,
                                        calc = nutrient.calc
                                    )
                                ) //create a NutrientData object
                            }
                            foodItemViewModel.foodItem.removeObserver(this)
                        }
                    }
                )
            }
            foodItemsAdapter.selectedItems = mutableListOf()
            foodItemsAdapter.searchIngredients(null)
            val searchTxt: TextView = view.findViewById(R.id.food_item_search_text)
            searchTxt.text = ""
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        foodItemsAdapter.selectedItems = mutableListOf()
        return super.onOptionsItemSelected(item)
    }

    override fun onResume() {
        super.onResume()
        Log.d("Resume: ", "Ingredients")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("Destroy: ", "Ingredients")
    }

    override fun onStop() {
        super.onStop()
        Log.d("Stop: ", "Ingredients")
    }

    override fun onPause() {
        super.onPause()
        Log.d("Pause: ", "Ingredients")
    }

    override fun onStart() {
        super.onStart()
        Log.d("Start: ", "Ingredients")
    }
}