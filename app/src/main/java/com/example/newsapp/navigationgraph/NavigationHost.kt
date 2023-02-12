package com.example.newsapp.navigationgraph

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.newsapp.View.NewsData
import com.example.newsapp.View.NewsDetails
import com.example.newsapp.ViewModel.NewsViewModel


sealed class Destination(val route: String) {
    object NewsData: Destination("NewsData")
    object NewsDetails: Destination("NewsDetails")
}

@Composable
fun NavigationHost() {

    val navigationController = rememberNavController()
    val viewModel: NewsViewModel = viewModel()

    NavHost(navigationController, startDestination = "NewsData") {

        composable(route = Destination.NewsData.route){
            NewsData(viewModel = viewModel,
                onNavigateToDetailScreen = {
                    navigationController.navigate("${Destination.NewsDetails.route}/$id")
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
