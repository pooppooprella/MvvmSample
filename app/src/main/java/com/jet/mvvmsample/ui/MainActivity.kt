package com.jet.mvvmsample.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.jet.mvvmsample.ui.NavigationKeys.Arg.FOOD_CATEGORY_ID
import com.jet.mvvmsample.ui.feature.categories.FoodCategoriesScreen
import com.jet.mvvmsample.ui.feature.categories.FoodCategoriesViewModel
import com.jet.mvvmsample.ui.feature.category_detail.FoodCategoryDetailsContract
import com.jet.mvvmsample.ui.feature.category_detail.FoodCategoryDetailsScreen
import com.jet.mvvmsample.ui.feature.category_detail.FoodCategoryDetailsViewModel
import com.jet.mvvmsample.ui.theme.MvvmSampleTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.receiveAsFlow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MvvmSampleTheme {
                FoodApp()
            }
        }
    }
}

@Composable
private fun FoodApp() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = NavigationKeys.Route.FOOD_CATEGORIES_LIST) {
        composable(route = NavigationKeys.Route.FOOD_CATEGORIES_LIST) {
            //음식목록화면
            FoodCategoriesDestination(navController)
        }

        composable(
            route = NavigationKeys.Route.FOOD_CATEGORY_DETAILS,
            arguments = listOf(navArgument(NavigationKeys.Arg.FOOD_CATEGORY_ID){
                type = NavType.StringType
            })
        ) {
            println("JSJ Detail go ")
            //음식 상세화면
            FoodCategoryDetailsDestination()
        }
    }
}

@Composable
private fun FoodCategoriesDestination(navController: NavController){
    val viewModel: FoodCategoriesViewModel = hiltViewModel()
    FoodCategoriesScreen(
        state = viewModel.state,
        effectFlow = viewModel.effects.receiveAsFlow(),
        onNavigationRequested = {itemId ->
            navController.navigate(route = "${NavigationKeys.Route.FOOD_CATEGORIES_LIST}/${itemId}")
        }
    )
}
@Composable
private fun FoodCategoryDetailsDestination() {
    val viewModel: FoodCategoryDetailsViewModel = hiltViewModel()
    FoodCategoryDetailsScreen(state = viewModel.state)
}

object NavigationKeys {
    object Arg {
        const val FOOD_CATEGORY_ID = "foodCategoryName"
    }
    object Route {
        const val FOOD_CATEGORIES_LIST = "food_categories_list"
        const val FOOD_CATEGORY_DETAILS = "$FOOD_CATEGORIES_LIST/{$FOOD_CATEGORY_ID}"
    }
}