package com.example.mealplan.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplan.R
import com.example.mealplan.data.Food

class IngredientsSelectionFragment : Fragment(R.layout.ingredients_selection_fragment) {
    private lateinit var ingredientsAdapter: IngredientsAdapter
    private lateinit var ingredientListRV: RecyclerView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ingredientListRV = view.findViewById(R.id.rv_ingredients)

        ingredientsAdapter = IngredientsAdapter(::onIngredientClick)

        ingredientListRV.layoutManager = LinearLayoutManager(requireContext())
        ingredientListRV.setHasFixedSize(true)
        ingredientListRV.adapter = ingredientsAdapter

        val search_btn: Button = view.findViewById(R.id.ingredient_search_btn)
        search_btn.setOnClickListener {
            val search_txt: TextView = view.findViewById(R.id.ingredient_search_text)
            val query = search_txt.text.toString()
            ingredientsAdapter.searchIngredients(query)
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