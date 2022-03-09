package com.example.mealplan.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplan.R
import com.example.mealplan.data.Recipe

class RecipeAdapter(private val onClick: (Recipe) -> Unit)
    : RecyclerView.Adapter<RecipeAdapter.ViewHolder>() {
    var recipes: MutableList<Recipe?> = mutableListOf()

    fun updateRecipes(recipe: Recipe?) {
        recipes.add(recipe)
        // add recipe to database
        notifyDataSetChanged()
    }

    override fun getItemCount() = this.recipes.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.recipe_list_item, parent, false)
        return RecipeAdapter.ViewHolder(view, onClick)
    }
    override fun onBindViewHolder(holder: RecipeAdapter.ViewHolder, position: Int) {
        holder.bind(this.recipes[position])
    }

    class ViewHolder(itemView: View, val onClick: (Recipe) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val name_tv: TextView = itemView.findViewById(R.id.list_recipe_name)

        private var currentRecipe: Recipe? = null

        init {
            itemView.setOnClickListener{
                currentRecipe?.let(onClick)
            }
        }

        fun bind(recipe: Recipe?) {
            currentRecipe = recipe

            val ctx = itemView.context

            name_tv.text = recipe?.name
        }
    }
}