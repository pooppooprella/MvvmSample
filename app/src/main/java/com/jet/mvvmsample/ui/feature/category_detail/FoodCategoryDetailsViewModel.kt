package com.jet.mvvmsample.ui.feature.category_detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jet.mvvmsample.model.data.FoodMenuRemoteSource
import com.jet.mvvmsample.ui.NavigationKeys
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.lang.IllegalStateException
import javax.inject.Inject

@HiltViewModel
class FoodCategoryDetailsViewModel @Inject constructor(
    private val stateHandler: SavedStateHandle,
    private val repository: FoodMenuRemoteSource
) : ViewModel(){
    var state by mutableStateOf(
        FoodCategoryDetailsContract.State(
            null, listOf()
        )
    )
        private set

    init {
        viewModelScope.launch {
            val categoryId = stateHandler.get<String>(NavigationKeys.Arg.FOOD_CATEGORY_ID)
                ?: throw IllegalStateException("No CategoryId was passed to Destination")
            val categories = repository.getFoodCategories()
            val category = categories.first{it.id == categoryId}
            state = state.copy(category = category)
            val foodItems = repository.getMealsByCategory(categoryId)
            state = state.copy(categoryFoodItem = foodItems)
        }
    }
}