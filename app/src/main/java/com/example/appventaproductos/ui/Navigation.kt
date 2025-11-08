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

        // Autenticación y entrada
        composable("login") {
            val vm: LoginViewModel = viewModel()
            LoginScreen(viewModel = vm, navController = navController)
        }
        composable("forgot_password") { ForgotPasswordScreen(navController) }
        composable("register") { RegisterScreen(navController) }
        composable("home") { HomeScreen(navController) }

        // Menú principal
        composable("menu") {
            val vm: MenuViewModel = viewModel()
            MenuScreen(viewModel = vm, navController = navController)
        }

        // Listas por categoría
        composable("carriola") {
            val vm: CarriolaViewModel = viewModel(factory = CarriolaViewModel.Factory)
            CarriolaScreen(viewModel = vm, navController = navController)
        }
        composable("ropa") {
            val vm: RopaViewModel = viewModel(factory = RopaViewModel.Factory)
            RopaScreen(viewModel = vm, navController = navController)
        }
        composable("accesorios") {
            val vm: AccesoriosViewModel = viewModel(factory = AccesoriosViewModel.Factory)
            AccesoriosScreen(viewModel = vm, navController = navController)
        }

        // Navegaciones dinámicas usadas por tus listas: "{categoria}/{id}"
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
            route = "accesorios/{id}",
            arguments = listOf(navArgument("id") { type = NavType.IntType })
        ) { backStack ->
            val id = backStack.arguments?.getInt("id") ?: -1
            EditProductScreen(navController = navController, category = "accesorios", id = id)
        }

        // Agregar producto (abre formulario con categoría inicial)
        composable(
            route = "product/add/{category}",
            arguments = listOf(navArgument("category") { type = NavType.StringType })
        ) { backStack ->
            val category = backStack.arguments?.getString("category") ?: "ropa"
            AddProductScreen(navController = navController, initialCategory = category)
        }

        // Editar producto genérico con categoría e id
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
