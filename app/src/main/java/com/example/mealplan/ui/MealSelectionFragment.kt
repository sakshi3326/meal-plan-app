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
import com.example.mealplan.data.Meal

class MealSelectionFragment: Fragment(R.layout.meal_selection_fragment) {
    private lateinit var mealAdapter: MealAdapter
    private lateinit var mealListRV: RecyclerView

    private val viewModel: MealViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mealListRV = view.findViewById(R.id.rv_meals)

        mealAdapter = MealAdapter(::onMealClick)

        mealListRV.layoutManager = LinearLayoutManager(requireContext())
        mealListRV.setHasFixedSize(true)
        mealListRV.adapter = mealAdapter

        val selection_date: TextView = view.findViewById(R.id.selection_date)
        val args: MealSelectionFragmentArgs by navArgs()
        val date = args.date
        selection_date.text = date

        viewModel.getMealByDate(date)

        viewModel.meals.observe(viewLifecycleOwner) { meals ->
            mealAdapter.updateMeals(meals)
        }

        val add_btn: Button = view.findViewById(R.id.add_meal_btn)
        add_btn.setOnClickListener{
            val add_txt: TextView = view.findViewById(R.id.add_meal_txt)
            val new_meal = Meal(add_txt.text.toString(), date)
            add_txt.text = null
            viewModel.addMeal(new_meal)
        }
    }

    private fun onMealClick(meal: Meal) {
        val directions = MealSelectionFragmentDirections.navigateFromMealSelectionToMealForm(meal)
        findNavController().navigate(directions)
    }

    //for debugging purposes in the lifecycle model

    override fun onResume() {
        Log.d("Resume: ", "MealSelection")
        val add_txt: TextView = requireView().findViewById(R.id.add_meal_txt)
        add_txt.text = null
        super.onResume()
    }

    override fun onDestroy() {
        Log.d("Destroy: ", "MealSelection")
        super.onDestroy()
    }

    override fun onStop() {
        Log.d("Stop: ", "MealSelection")
        super.onStop()
    }

    override fun onPause() {
        Log.d("Pause: ", "MealSelection")
        super.onPause()
    }

    override fun onStart() {
        Log.d("Start: ", "MealSelection")
        super.onStart()
    }
}