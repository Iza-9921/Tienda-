package com.example.appventaproductos.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.appventaproductos.ui.components.RopaCard
import com.example.appventaproductos.viewmodel.RopaViewModel

@Composable
fun RopaScreen(
    navController: NavHostController
) {
    val viewModel: RopaViewModel = viewModel(factory = RopaViewModel.Factory)
    val ropaList by viewModel.ropaList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    if (isLoading) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(ropaList) { ropa ->
                RopaCard(
                    ropa = ropa,
                    onClick = {
                        navController.navigate("ropa/${ropa.id}")
                    }
                )
            }
        }
    }
}