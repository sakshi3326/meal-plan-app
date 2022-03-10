package com.example.mealplan.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mealplan.R
import java.text.DateFormat
import java.util.*

class CalendarSelectionFragment : Fragment(R.layout.calendar_selection_fragment) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)

        var calendar = Calendar.getInstance()

        val myCalendar: CalendarView = view.findViewById(R.id.calendar)

        var date = ""

        myCalendar.setOnDateChangeListener {_, year, month, day ->
            calendar.set(year, month, day)
            myCalendar.date = calendar.timeInMillis
            val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM)
            date = dateFormat.format(calendar.time)
        }

        val mealSelBtn: Button = view.findViewById(R.id.meal_select)
        mealSelBtn.setOnClickListener {
            val directions = CalendarSelectionFragmentDirections.navigateFromCalendarSelectionToMealSelection(date)
            findNavController().navigate(directions)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, menuInflater: MenuInflater) {
        menuInflater.inflate(R.menu.calendar_selection, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.manage_recipes_item -> {
                val directions = CalendarSelectionFragmentDirections.navigateFromCalendarSelectionToRecipeSelection("MainActivity", null)
                findNavController().navigate(directions)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}