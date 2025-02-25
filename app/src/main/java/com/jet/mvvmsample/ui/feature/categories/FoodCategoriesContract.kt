package com.jet.mvvmsample.ui.feature.categories

import com.jet.mvvmsample.model.FoodItem

class FoodCategoriesContract {
    data class State(
        val categories: List<FoodItem> = listOf(),
        val isLoading: Boolean = false
    )

    sealed class Effect {
        object DataWasLoaded : Effect()
    }
}