package com.example.appventaproductos.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import com.example.appventaproductos.ui.components.texts.CarriolaList
import com.example.appventaproductos.viewmodel.CarriolasViewModel

@Composable
fun CarriolaListScreen(
    navController: NavController,
    viewModel: CarriolasViewModel
) {
    val lista by viewModel.carriolas.collectAsState()

    CarriolaList(lista) { carriola ->
        // Navegar al detalle usando el id
        navController.navigate("carriola_detail/${carriola.id}")
    }
}
