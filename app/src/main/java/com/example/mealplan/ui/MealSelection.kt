package com.example.mealplan.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplan.R
import com.example.mealplan.data.Meal

class MealSelection: AppCompatActivity() {
    private lateinit var mealAdapter: MealAdapter
    private lateinit var mealListRV: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.meal_selection)

        mealListRV = findViewById(R.id.rv_meals)

        mealAdapter = MealAdapter(::onMealClick)

        mealListRV.layoutManager = LinearLayoutManager(this)
        mealListRV.setHasFixedSize(true)
        mealListRV.adapter = mealAdapter

        val selection_date: TextView = findViewById(R.id.selection_date)
        selection_date.text = getIntent().extras?.get("date").toString()

        //this will look different once we've implemented full functionality. For now, just render for example
        mealAdapter.updateMeals(Meal("Breakfast"))
        mealAdapter.updateMeals(Meal("Lunch"))
        mealAdapter.updateMeals(Meal("Dinner"))

        val add_btn: Button = findViewById(R.id.add_meal_btn)
        add_btn.setOnClickListener{
            val add_txt: TextView = findViewById(R.id.add_meal_txt)
            val new_meal = Meal(add_txt.text.toString())
            mealAdapter.updateMeals(new_meal)
        }
    }

    private fun onMealClick(meal: Meal) {
        val intent = Intent(this, MealActivity::class.java)
        intent.putExtra("name", meal.name)
        startActivity(intent)
    }

    //for debugging purposes in the lifecycle model

    override fun onResume() {
        Log.d("Resume: ", "MealSelection")
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

    override fun onRestart() {
        Log.d("Restart: ", "MealSelection")
        super.onRestart()
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