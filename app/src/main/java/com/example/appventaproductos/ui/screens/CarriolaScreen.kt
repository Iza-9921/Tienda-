package com.example.appventaproductos.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.appventaproductos.ui.components.CarriolaList
import com.example.appventaproductos.viewmodel.CarriolaViewModel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

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
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("product/add/carriola") }) {
                Icon(Icons.Filled.Add, contentDescription = "Añadir carriola")
            }
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
                CarriolaList(
                    lista = lista,
                    onClick = { item ->
                        navController.navigate("carriola/${item.id}")
                    },
                    on3dClick = { item ->
                        val encodedUrl = URLEncoder.encode(item.modelo3d, StandardCharsets.UTF_8.toString())
                        navController.navigate("view_3d/$encodedUrl")
                    }
                )
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