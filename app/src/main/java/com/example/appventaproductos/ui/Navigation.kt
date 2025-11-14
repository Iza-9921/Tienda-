package com.example.appventaproductos.ui

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.appventaproductos.ui.screens.*
import com.example.appventaproductos.viewmodel.*

@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            val vm: LoginViewModel = viewModel()
            LoginScreen(viewModel = vm, navController = navController)
        }
        composable("forgot_password") { ForgotPasswordScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }

        composable("menu") {
            val vm: MenuViewModel = viewModel()
            MenuScreen(viewModel = vm, navController = navController)
        }

        composable("carriola") {
            CarriolaScreen(navController = navController)
        }
        composable("ropa") {
            RopaScreen(navController = navController)
        }
        composable("accesorio") {
            val vm: AccesorioViewModel = viewModel(factory = AccesorioViewModel.Factory)
            AccesorioScreen(viewModel = vm, navController = navController)
        }

        composable(
            route = "ropa/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStack ->
            val id = backStack.arguments?.getInt("id") ?: -1
            EditProductScreen(navController = navController, category = "ropa", id = id)
        }
        composable(
            route = "carriola/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStack ->
            val id = backStack.arguments?.getInt("id") ?: -1
            EditProductScreen(navController = navController, category = "carriola", id = id)
        }
        composable(
            route = "accesorio/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStack ->
            val id = backStack.arguments?.getInt("id") ?: -1
            EditProductScreen(navController = navController, category = "accesorio", id = id)
        }

        composable(
            route = "product/add/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStack ->
            val category = backStack.arguments?.getString("category") ?: "ropa"
            AddProductScreen(navController = navController, initialCategory = category)
        }

        composable(
            route = "product/edit/{category}/{id}",
            arguments = listOf(
                navArgument("category") { type = NavType.StringType },
                navArgument("id") { type = NavType.IntType }
            )
        ) { backStack ->
            val category = backStack.arguments?.getString("category") ?: "ropa"
            val id = backStack.arguments?.getInt("id") ?: -1
            EditProductScreen(navController = navController, category = category, id = id)
        }
    }
}