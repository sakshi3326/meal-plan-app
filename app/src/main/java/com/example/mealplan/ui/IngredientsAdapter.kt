package com.example.mealplan.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplan.R
import com.example.mealplan.data.Food
import com.example.mealplan.data.IngredientsList

class IngredientsAdapter(private val onClick: (Food) -> Unit)
    : RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder>(){
    var ingredients: MutableList<Food> = mutableListOf()

    fun searchIngredients(ingredientsList : IngredientsList?) {
        if (ingredientsList != null) {
            ingredients = ingredientsList.results.toMutableList()
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = this.ingredients.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredient_list_item, parent, false)
        return IngredientsViewHolder(view, onClick)
    }
    override fun onBindViewHolder(holder: IngredientsViewHolder, position: Int) {
        holder.bind(this.ingredients[position])
    }

    class IngredientsViewHolder(itemView: View, private val onClick: (Food) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val nameTv: TextView = itemView.findViewById(R.id.list_ingredient_name)
        private var currentIngredient: Food? = null

        init {
            itemView.setOnClickListener{
                currentIngredient?.let(onClick)
            }
        }

        fun bind(ingredient: Food?) {
            currentIngredient = ingredient

            val ctx = itemView.context

            nameTv.text = ingredient?.name
        }
    }
}