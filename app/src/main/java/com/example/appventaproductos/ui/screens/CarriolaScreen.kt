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
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.appventaproductos.data.model.Carriola
import com.example.appventaproductos.ui.components.CarriolaCard
import com.example.appventaproductos.ui.components.CarriolaList
import com.example.appventaproductos.ui.components.texts.Label
import com.example.appventaproductos.ui.components.texts.Title
import com.example.appventaproductos.viewmodel.CarriolaViewModel

@Composable
fun CarriolaScreen(viewModel: CarriolaViewModel, navController: NavHostController) {

    Column {
        Title("Personas registradas en morelos con licencia")
        val carriola by viewModel.carriola.collectAsState(initial = emptyList())
        CarriolaList(carriola) { viewModel::clickPerson }
        Label("Fin de la lista")
    }
}