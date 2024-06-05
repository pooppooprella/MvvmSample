package com.jet.mvvmsample.ui.feature.category_detail

import com.jet.mvvmsample.model.FoodItem

class FoodCategoryDetailsContract {
    data class State(
        val category: FoodItem?,
        val categoryFoodItem: List<FoodItem>
    )
}