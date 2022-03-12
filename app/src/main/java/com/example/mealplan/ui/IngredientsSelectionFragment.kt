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
import com.example.mealplan.data.Food
import com.example.mealplan.data.LoadingStatus
import com.google.android.material.progressindicator.CircularProgressIndicator

class IngredientsSelectionFragment : Fragment(R.layout.ingredients_selection_fragment) {
    private var ingredientsAdapter = IngredientsAdapter(::onIngredientClick)
    private val viewModel: IngredientsViewModel by viewModels()

    private lateinit var ingredientListRV: RecyclerView
    private lateinit var loadingIndicator: CircularProgressIndicator
    private lateinit var errorText : TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        loadingIndicator = view.findViewById(R.id.loading_indicator)
        errorText = view.findViewById(R.id.tv_search_error)
        ingredientListRV = view.findViewById(R.id.rv_ingredients)

        ingredientListRV.layoutManager = LinearLayoutManager(requireContext())
        ingredientListRV.setHasFixedSize(true)

        ingredientListRV.adapter = ingredientsAdapter

        val searchBtn: Button = view.findViewById(R.id.ingredient_search_btn)
        searchBtn.setOnClickListener {
            val searchTxt: TextView = view.findViewById(R.id.ingredient_search_text)
            val query = searchTxt.text.toString()
            viewModel.searchIngredients(query)
            ingredientListRV.scrollToPosition(0)
        }

        val clearBtn: Button = view.findViewById(R.id.ingredient_clear_btn)
        clearBtn.setOnClickListener {
            val searchTxt: TextView = view.findViewById(R.id.ingredient_search_text)
            searchTxt.text = ""
        }

        viewModel.ingredients.observe(viewLifecycleOwner) { ingredients ->
            ingredientsAdapter.searchIngredients(ingredients)
        }

        viewModel.loadingStatus.observe(viewLifecycleOwner) { uiState ->
            when(uiState) {
                LoadingStatus.LOADING -> {
                    loadingIndicator.visibility = View.VISIBLE
                    ingredientListRV.visibility = View.INVISIBLE
                    errorText.visibility = View.INVISIBLE
                }
                LoadingStatus.ERROR -> {
                    loadingIndicator.visibility = View.INVISIBLE
                    ingredientListRV.visibility = View.INVISIBLE
                    errorText.visibility = View.VISIBLE
                }
                else -> {
                    loadingIndicator.visibility = View.INVISIBLE
                    ingredientListRV.visibility = View.VISIBLE
                    errorText.visibility = View.INVISIBLE
                }
            }
        }
    }

    private fun onIngredientClick(ingredient: Food) {
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