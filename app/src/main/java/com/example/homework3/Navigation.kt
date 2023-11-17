package com.example.homework3.ui.navigation

import CityListScreen
import SecondScreen
import WelcomeScreen
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screen(val route: String) {
    object WelcomeScreen : Screen("welcome_screen")
    object SecondScreen : Screen("second_screen")
    object CityListScreen : Screen("city_list_screen")
}

@Composable
fun SetupNavigation(navController: NavController) {
    NavHost(
        navController = navController,
        startDestination = Screen.WelcomeScreen.route
    ) {
        composable(Screen.WelcomeScreen.route) {
            WelcomeScreen(navController = navController)
        }
        composable(Screen.SecondScreen.route) {
           SecondScreen(navController)
        }
        composable(Screen.CityListScreen.route) {
            CityListScreen(navController)
        }    }
}



