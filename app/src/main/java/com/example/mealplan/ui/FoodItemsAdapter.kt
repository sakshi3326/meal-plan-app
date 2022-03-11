package com.example.mealplan.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplan.R
import com.example.mealplan.data.FoodItem
import com.example.mealplan.data.FoodItemsList

class FoodItemsAdapter(private val onClick: (FoodItem) -> Unit)
    : RecyclerView.Adapter<FoodItemsAdapter.FoodItemsViewHolder>(){
    var foodItems: MutableList<FoodItem> = mutableListOf()

    fun searchIngredients(foodItemsList : FoodItemsList?) {
        if (foodItemsList != null) {
            foodItems = foodItemsList.results.toMutableList()
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = this.foodItems.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodItemsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.food_item_list_item, parent, false)
        return FoodItemsViewHolder(view, onClick)
    }
    override fun onBindViewHolder(holder: FoodItemsViewHolder, position: Int) {
        holder.bind(this.foodItems[position])
    }

    class FoodItemsViewHolder(itemView: View, private val onClick: (FoodItem) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val nameTv: TextView = itemView.findViewById(R.id.list_food_item_name)
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