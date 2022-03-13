package com.example.mealplan.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplan.R
import com.example.mealplan.data.FoodItem
import com.example.mealplan.data.LoadingStatus
import com.google.android.material.progressindicator.CircularProgressIndicator

class FoodItemsSelectionFragment : Fragment(R.layout.food_item_selection_fragment) {
    private var foodItemsAdapter = FoodItemsAdapter(::onFoodItemClick)
    private val viewModel: FoodItemsViewModel by viewModels()

    private lateinit var foodItemListRV: RecyclerView
    private lateinit var loadingIndicator: CircularProgressIndicator
    private lateinit var errorText : TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingIndicator = view.findViewById(R.id.loading_indicator)
        errorText = view.findViewById(R.id.tv_search_error)
        foodItemListRV = view.findViewById(R.id.rv_food_items)

        foodItemListRV.layoutManager = LinearLayoutManager(requireContext())
        foodItemListRV.setHasFixedSize(true)

        foodItemListRV.adapter = foodItemsAdapter

        val searchBtn: Button = view.findViewById(R.id.food_item_search_btn)
        searchBtn.setOnClickListener {
            val searchTxt: TextView = view.findViewById(R.id.food_item_search_text)
            val query = searchTxt.text.toString()
            viewModel.searchIngredients(query)
            foodItemListRV.scrollToPosition(0)
        }

        viewModel.foodItems.observe(viewLifecycleOwner) { foodItem ->
            foodItemsAdapter.searchIngredients(foodItem)
        }
        val clearBtn: Button = view.findViewById(R.id.ingredient_clear_btn)
        clearBtn.setOnClickListener {
            val searchTxt: TextView = view.findViewById(R.id.food_item_search_text)
            searchTxt.text = ""
        }

        viewModel.loadingStatus.observe(viewLifecycleOwner) { uiState ->
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
    }

    private fun onFoodItemClick(foodItem: FoodItem) {
        // save either the recipe or meal passed into the args with this ingredient added
        findNavController().navigateUp()
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