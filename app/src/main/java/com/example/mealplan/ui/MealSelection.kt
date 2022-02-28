package com.example.mealplan

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MealSelection: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.meal_selection)

        val selection_date: TextView = findViewById(R.id.selection_date)
        selection_date.text = getIntent().extras?.get("date").toString()
    }
}