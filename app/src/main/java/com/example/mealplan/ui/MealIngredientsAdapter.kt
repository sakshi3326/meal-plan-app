package com.example.mealplan.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplan.R
import com.example.mealplan.data.FoodItem
import com.example.mealplan.data.FoodItemsList

class MealIngredientsAdapter(private val onClick: (FoodItem) -> Unit)
	: RecyclerView.Adapter<MealIngredientsAdapter.MealIngredientsHolder>(){
	var mealIngredients: MutableList<FoodItem> = mutableListOf()

	fun searchIngredients(foodItemsList : FoodItemsList?) {
		if (foodItemsList != null) {
			mealIngredients = foodItemsList.results.toMutableList()
			notifyDataSetChanged()
		}
	}

	override fun getItemCount() = this.mealIngredients.size

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealIngredientsHolder {
		val view = LayoutInflater.from(parent.context)
			.inflate(R.layout.meal_ingredient_list_item, parent, false)
		return MealIngredientsHolder(view, onClick)
	}
	override fun onBindViewHolder(holder: MealIngredientsHolder, position: Int) {
		holder.bind(this.mealIngredients[position])
	}

	class MealIngredientsHolder(itemView: View, private val onClick: (FoodItem) -> Unit) : RecyclerView.ViewHolder(itemView) {
		private val nameTv: TextView = itemView.findViewById(R.id.list_meal_ingredient_name)
		private var currentFoodItem: FoodItem? = null

		init {
			itemView.setOnClickListener{
				currentFoodItem?.let(onClick)
			}
		}

		fun bind(foodItem: FoodItem?) {
			currentFoodItem = foodItem

			val ctx = itemView.context

			nameTv.text = foodItem?.name
		}
	}
}