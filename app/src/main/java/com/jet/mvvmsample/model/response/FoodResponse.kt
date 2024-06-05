package com.jet.mvvmsample.model.response

import com.google.gson.annotations.SerializedName
import com.jet.mvvmsample.model.FoodItem

data class FoodCategoriesResponse(val categories: List<FoodCategoryResponse>)
data class MealsResponse(val meals: List<MealResponse>)

data class FoodCategoryResponse(
    @SerializedName("idCategory") val id: String,
    @SerializedName("strCategory")val name: String,
    @SerializedName("strCategoryThumb")val thumgnailUrl: String,
    @SerializedName("strCategoryDescription")val description: String = ""
)

data class MealResponse(
    @SerializedName("idMeal") val id: String,
    @SerializedName("strMeal") val name: String,
    @SerializedName("strMealThumb") val thumbnailUrl: String,

)

fun FoodCategoriesResponse.mapCategoriesToItems(): List<FoodItem> {
    return this.categories.map { category ->
        FoodItem(
            id = category.id,
            name = category.name,
            description = category.description,
            thumbnailUrl = category.thumgnailUrl
        )
    }
}

fun MealsResponse.mapMealsToItems(): List<FoodItem> {
    return this.meals.map { category ->
        FoodItem(
            id = category.id,
            name = category.name,
            thumbnailUrl = category.thumbnailUrl
        )
    }
}