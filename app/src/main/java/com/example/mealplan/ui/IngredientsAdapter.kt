package com.example.mealplan.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplan.R
import com.example.mealplan.data.Food

class IngredientsAdapter(private val onClick: (Food) -> Unit)
    : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>(){
    var ingredients: MutableList<Food?> = mutableListOf()

    fun searchIngredients(query: String) {
        // perform API search and update ingredients
        //ingredients.add(Food(query)) // won't want to keep this - just proof of concept
        notifyDataSetChanged()
    }

    override fun getItemCount() = this.ingredients.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ingredient_list_item, parent, false)
        return IngredientsAdapter.ViewHolder(view, onClick)
    }
    override fun onBindViewHolder(holder: IngredientsAdapter.ViewHolder, position: Int) {
        holder.bind(this.ingredients[position])
    }

    class ViewHolder(itemView: View, val onClick: (Food) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val name_tv: TextView = itemView.findViewById(R.id.list_ingredient_name)

        private var currentIngredient: Food? = null

        init {
            itemView.setOnClickListener{
                currentIngredient?.let(onClick)
            }
        }

        fun bind(ingredient: Food?) {
            currentIngredient = ingredient

            val ctx = itemView.context

            name_tv.text = ingredient?.name
        }
    }
}