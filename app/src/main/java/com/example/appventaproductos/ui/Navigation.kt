package com.example.appventaproductos.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appventaproductos.ui.components.texts.CarriolaList
import com.example.appventaproductos.ui.screens.CarriolaListScreen
import com.example.appventaproductos.ui.screens.CarriolaScreen
import com.example.appventaproductos.ui.screens.ForgotPasswordScreen
import com.example.appventaproductos.ui.screens.HomeScreen
import com.example.appventaproductos.ui.screens.LoginScreen
import com.example.appventaproductos.ui.screens.RegisterScreen
import com.example.appventaproductos.viewmodel.CarriolasViewModel
import com.example.appventaproductos.viewmodel.LoginViewModel


@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            val viewModel: LoginViewModel = viewModel() // instancia del ViewModel
            LoginScreen(viewModel = viewModel, navController = navController)
        }
        composable("forgot_password") { ForgotPasswordScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }

        composable("carriola_list") {
            val viewModel: CarriolasViewModel = viewModel()
            CarriolaListScreen(navController = navController, viewModel = viewModel)
        }
        composable(
            "carriola_detail/{carriolaId}",
            arguments = listOf(navArgument("carriolaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val carriolaId = backStackEntry.arguments?.getInt("carriolaId") ?: 0
            val viewModel: CarriolasViewModel = viewModel()
            val carriola = viewModel.carriolas.collectAsState().value.firstOrNull { it.id == carriolaId }
            carriola?.let {
                CarriolaScreen(carriola = it, navController = navController)
            }
        }
    }
}
