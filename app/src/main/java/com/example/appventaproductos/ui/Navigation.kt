package com.example.appventaproductos.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.appventaproductos.data.model.Accesorios
import com.example.appventaproductos.ui.screens.AccesoriosScreen
import com.example.appventaproductos.ui.screens.CarriolaScreen
import com.example.appventaproductos.ui.screens.ForgotPasswordScreen
import com.example.appventaproductos.ui.screens.HomeScreen
import com.example.appventaproductos.ui.screens.LoginScreen
import com.example.appventaproductos.ui.screens.MenuScreen
import com.example.appventaproductos.ui.screens.RegisterScreen
import com.example.appventaproductos.ui.screens.RopaScreen
import com.example.appventaproductos.viewmodel.AccesoriosViewModel
import com.example.appventaproductos.viewmodel.CarriolaViewModel
import com.example.appventaproductos.viewmodel.LoginViewModel
import com.example.appventaproductos.viewmodel.MenuViewModel
import com.example.appventaproductos.viewmodel.RopaViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            val viewModel: LoginViewModel = viewModel()
            LoginScreen(viewModel = viewModel, navController = navController)
        }
        composable("forgot_password") { ForgotPasswordScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }


        composable("menu") {
            val  viewModel: MenuViewModel = viewModel()
            MenuScreen(viewModel = viewModel, navController = navController)
        }
        //ruta de carriola
        composable("carriola") {
            val viewModel: CarriolaViewModel = viewModel()
            CarriolaScreen(viewModel = viewModel, navController = navController)
        }
        composable("ropa") {
            val viewModel: RopaViewModel = viewModel()
            RopaScreen(viewModel = viewModel, navController = navController)
        }
        composable("accesorios") {
            val viewModel: AccesoriosViewModel = viewModel()
            AccesoriosScreen(viewModel = viewModel, navController = navController)
        }
    }
}