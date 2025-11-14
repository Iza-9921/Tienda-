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
import com.example.appventaproductos.ui.components.AccesorioList
import com.example.appventaproductos.viewmodel.AccesorioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccesorioScreen(
    viewModel: AccesorioViewModel,
    navController: NavHostController
) {
    val lista by viewModel.accesorioList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Accesorio de bebé") }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate("product/add/accesorio")
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Añadir accesorio")
            }
        },
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection)
    ) { inner ->
        if (isLoading) {
            Box(
                modifier = Modifier
                    .padding(inner)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        } else if (lista.isEmpty()) {
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
                AccesorioList(lista) { item ->
                    navController.navigate("accesorio/${item.id}")
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