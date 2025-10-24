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
import com.example.appventaproductos.ui.components.AccesoriosList
import com.example.appventaproductos.viewmodel.AccesoriosViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AccesoriosScreen(
    viewModel: AccesoriosViewModel,
    navController: NavHostController
) {
    val lista by viewModel.accesorios.collectAsState(initial = emptyList())

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Accesorios de bebé") }
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
                AccesoriosList(lista) { item ->

                    navController.navigate("accesorios/${item.id}")
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