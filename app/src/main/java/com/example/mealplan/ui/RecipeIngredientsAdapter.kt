package com.example.mealplan.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplan.R
import com.example.mealplan.data.FoodItemData

class RecipeIngredientsAdapter(private val onClick: (FoodItemData) -> Unit)
	: RecyclerView.Adapter<RecipeIngredientsAdapter.RecipeIngredientsHolder>(){
	var recipeIngredients: MutableList<FoodItemData> = mutableListOf()

	fun showIngredients(ingredientsList : List<FoodItemData>?) {
		if (ingredientsList != null) {
			recipeIngredients = ingredientsList.toMutableList()
			notifyDataSetChanged()
		}
	}

	override fun getItemCount() = this.recipeIngredients.size

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeIngredientsHolder {
		val view = LayoutInflater.from(parent.context)
			.inflate(R.layout.recipe_ingredient_list_item, parent, false)
		return RecipeIngredientsHolder(view, onClick)
	}
	override fun onBindViewHolder(holder: RecipeIngredientsHolder, position: Int) {
		holder.bind(this.recipeIngredients[position])
	}

	class RecipeIngredientsHolder(itemView: View, private val onClick: (FoodItemData) -> Unit) : RecyclerView.ViewHolder(itemView) {
		private val nameTv: TextView = itemView.findViewById(R.id.list_recipe_ingredient_name)
		private var currentFoodItem: FoodItemData? = null

		init {
			itemView.setOnClickListener{
				currentFoodItem?.let(onClick)
			}
		}

		fun bind(foodItem: FoodItemData) {
			currentFoodItem = foodItem

			val ctx = itemView.context

			nameTv.text = foodItem?.name
		}
	}
}