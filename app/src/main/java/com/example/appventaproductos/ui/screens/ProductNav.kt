package com.example.appventaproductos.ui.screens

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.NavType

fun NavGraphBuilder.productNav(navController: NavHostController) {
    composable(
        route = "product/add/{category}",
        arguments = listOf(navArgument("category") { type = NavType.StringType })
    ) { backStackEntry ->
        val category = backStackEntry.arguments?.getString("category") ?: "ropa"
        AddProductScreen(navController = navController, initialCategory = category)
    }

    composable(
        route = "product/edit/{category}/{id}",
        arguments = listOf(
            navArgument("category") { type = NavType.StringType },
            navArgument("id") { type = NavType.IntType }
        )
    ) { backStackEntry ->
        val category = backStackEntry.arguments?.getString("category") ?: "ropa"
        val id = backStackEntry.arguments?.getInt("id") ?: -1
        EditProductScreen(navController = navController, category = category, id = id)
    }
}
