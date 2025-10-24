package com.example.appventaproductos.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appventaproductos.ui.components.CarriolaList
import com.example.appventaproductos.viewmodel.CarriolaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CarriolaScreen(
    viewModel: CarriolaViewModel,
    navController: NavHostController
) {
    val lista by viewModel.carriola.collectAsState(initial = emptyList())

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Carriolas") }
            )
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { inner ->
        if (lista.isEmpty()) {
            Box(
                modifier = Modifier
                    .padding(inner)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("Sin productos")
            }
        } else {
            Column(Modifier.padding(inner)) {
                CarriolaList(lista) { item ->
                    // Guarda selección si hace falta y navega al detalle
                    navController.navigate("carriola/${item.id}")
                }
                Spacer(Modifier.height(8.dp))
                Text(
                    "Fin de la lista",
                    style = MaterialTheme.typography.labelMedium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}
