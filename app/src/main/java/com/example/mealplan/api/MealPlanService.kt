package com.example.mealplan.api

import com.example.mealplan.data.IngredientsList
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface MealPlanService {
	//https://api.nal.usda.gov/fdc/v1/foods/search?api_key=kkYBL2fNKTOGlVPzlcndfmcXQ0KhGgL0Y3McXwgn&query=
	@GET("search")
	suspend fun searchIngredients(
		@Query("query") query: String?,
		@Query("api_key") apiKey: String = "kkYBL2fNKTOGlVPzlcndfmcXQ0KhGgL0Y3McXwgn"
	) : IngredientsList

	companion object {
		private const val BASE_URL = "https://api.nal.usda.gov/fdc/v1/foods/"

		/**
		 * This method is used to create an instance of the [MealPlanService] interface.
		 */
		fun create() : MealPlanService {
			val moshi = Moshi.Builder()
				.addLast(KotlinJsonAdapterFactory())
				.build()
			return Retrofit.Builder()
				.baseUrl(BASE_URL)
				.addConverterFactory(MoshiConverterFactory.create(moshi))
				.build()
				.create(MealPlanService::class.java)
		}
	}
}
