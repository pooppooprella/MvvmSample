package com.jet.mvvmsample.model.data

import android.util.Log
import com.jet.mvvmsample.model.response.mapCategoriesToItems
import com.jet.mvvmsample.model.response.mapMealsToItems
import com.jet.mvvmsample.model.FoodItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FoodMenuRemoteSource @Inject constructor(private val foodMenuApi: FoodMenuApi){
    private var _cachedCategories: List<FoodItem>? = null

    suspend fun getFoodCategories(): List<FoodItem> = withContext(Dispatchers.IO){
        var cachedCategories = _cachedCategories
        if(cachedCategories == null) {
            cachedCategories = foodMenuApi.getFoodCategories().mapCategoriesToItems()
            this@FoodMenuRemoteSource._cachedCategories = cachedCategories
        }
        return@withContext cachedCategories
    }

    suspend fun getMealsByCategory(categoryId: String) = withContext(Dispatchers.IO) {
        val categoryName = getFoodCategories().first { it.id == categoryId }.name
        println("JSJ getMealsByCategory categoryName = $categoryName")
        return@withContext foodMenuApi.getMealsByCategory(categoryName).mapMealsToItems()
    }

}

