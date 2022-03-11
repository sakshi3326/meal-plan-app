package com.example.mealplan.ui

import android.util.Log
import android.view.ViewGroup
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplan.data.*
import android.view.LayoutInflater
import android.widget.TextView
import com.example.mealplan.R

class MealAdapter(private val onClick: (Meal) -> Unit)
    : RecyclerView.Adapter<MealAdapter.ViewHolder>() {
    var meals: MutableList<Meal?> = mutableListOf()

    fun updateMeals(mealList: List<Meal>?) {
        if (mealList != null) {
            meals = mealList.toMutableList()
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = this.meals.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.meal_list_item, parent, false)
        return ViewHolder(view, onClick)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.meals[position])
    }

    class ViewHolder(itemView: View, val onClick: (Meal) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val name_tv: TextView = itemView.findViewById(R.id.list_meal_name)

        private var currentMeal: Meal? = null

        init {
            itemView.setOnClickListener{
                currentMeal?.let(onClick)
            }
        }

        fun bind(meal: Meal?) {
            currentMeal = meal

            val ctx = itemView.context

            name_tv.text = meal?.name
        }
    }
}