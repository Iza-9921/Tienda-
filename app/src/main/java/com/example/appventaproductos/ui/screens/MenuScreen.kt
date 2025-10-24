package com.example.appventaproductos.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appventaproductos.ui.components.InfoCard
import com.example.appventaproductos.viewmodel.MenuViewModel

@Composable
fun MenuScreen(viewModel: MenuViewModel, navController: NavController) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier.padding(30.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()){
            InfoCard(
                "Carreola",
                "Carreolas en descuento",
                Modifier.weight(1f)
                    .clickable{viewModel.goToCarriola(navController)},
                viewModel
            )

        }
    }
}