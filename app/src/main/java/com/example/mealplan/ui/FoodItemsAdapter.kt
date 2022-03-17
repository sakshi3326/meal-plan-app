package com.example.mealplan.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplan.R
import com.example.mealplan.data.FoodItem
import com.example.mealplan.data.FoodItemsList
import com.example.mealplan.data.Meal

class FoodItemsAdapter()
    : RecyclerView.Adapter<FoodItemsAdapter.FoodItemsViewHolder>(){
    var foodItems: MutableList<FoodItem> = mutableListOf()
    var selectedItems: MutableList<FoodItem> = mutableListOf()

    fun searchIngredients(foodItemsList : FoodItemsList?) {
        if (foodItemsList != null) {
            foodItems = foodItemsList.results.toMutableList()
        }
        else {
            foodItems = mutableListOf()
        }
        notifyDataSetChanged()
    }

    fun toggleItem(foodItem: FoodItem) {
        if (selectedItems.contains(foodItem)) {
            selectedItems.removeAt(selectedItems.indexOf(foodItem))
        }
        else
            selectedItems.add(foodItem)
    }

    override fun getItemCount() = this.foodItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_item_list_item, parent, false)
        return FoodItemsViewHolder(view)
    }
    override fun onBindViewHolder(holder: FoodItemsViewHolder, position: Int) {
        holder.bind(this.foodItems[position], ::toggleItem)
    }

    class FoodItemsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val nameTv: TextView = itemView.findViewById(R.id.list_food_item_name)
        private val checkBox: CheckBox = itemView.findViewById(R.id.check_ingredient)
        private var currentFoodItem: FoodItem? = null

        fun bind(foodItem: FoodItem, toggleItem: (FoodItem) -> Unit) {
            currentFoodItem = foodItem

            val ctx = itemView.context
            nameTv.text = foodItem.name + " - " + foodItem.servingSize + " calories"
            checkBox.setOnClickListener{
                toggleItem(foodItem)
            }
        }
    }
}