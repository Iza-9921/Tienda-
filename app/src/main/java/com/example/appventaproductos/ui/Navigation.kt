package com.example.appventaproductos.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appventaproductos.ui.screens.ForgotPasswordScreen
import com.example.appventaproductos.ui.screens.HomeScreen
import com.example.appventaproductos.ui.screens.LoginScreen
import com.example.appventaproductos.ui.screens.RegisterScreen
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
    }
}