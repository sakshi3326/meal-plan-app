package com.example.mealplan.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import java.util.*
import android.util.Log
import com.example.mealplan.R
import java.text.DateFormat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var calendar = Calendar.getInstance()

        val myCalendar: CalendarView = findViewById(R.id.calendar)
        var date: String? = null

        myCalendar.setOnDateChangeListener {view, year, month, day ->
            calendar.set(year, month, day)
            myCalendar.date = calendar.timeInMillis
            val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM)
            date = dateFormat.format(calendar.time)
        }

        val mealSelBtn: Button = findViewById(R.id.meal_select)
        mealSelBtn.setOnClickListener {
            val intent = Intent(this, MealSelection::class.java)
            intent.putExtra("date", date)
            startActivity(intent)
        }
    }
}