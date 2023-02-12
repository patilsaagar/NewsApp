package com.example.newsapp.Navigationgraph

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.newsapp.View.NewsData
import com.example.newsapp.View.NewsDetails
import com.example.newsapp.ViewModel.NewsViewModel


sealed class Destination(val route: String) {
    object NewsData: Destination("NewsData")
    object NewsDetails: Destination("NewsDetails")
}

@Composable
fun NavigationHost(navController: NavHostController,
                   viewModel: NewsViewModel
) {
    NavHost(navController, startDestination = "NewsData") {

        composable(route = "NewsData"){
            NewsData(viewModel = viewModel,
                onNavigateToDetailScreen = {
                    Log.d("1111111", "1111111{$it}")
                    navController.navigate("${Destination.NewsDetails.route}/$id")
                }
            )
        }

        composable(
            route = "NewsDetails/{inputName}",
            arguments = listOf(
                navArgument("inputName"){
                    type = NavType.StringType
                }
            )
        ){
            NewsDetails(
                newsURL = it.arguments?.getString("inputName").toString()
            )
        }
    }
}
