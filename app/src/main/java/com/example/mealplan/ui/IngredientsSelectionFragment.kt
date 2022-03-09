package com.example.mealplan.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import com.example.mealplan.R

class IngredientsSelectionFragment : Fragment(R.layout.ingredients_selection_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Create: ", "Ingredients")
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