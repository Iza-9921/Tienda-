package com.example.appventaproductos.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.appventaproductos.R
import com.example.appventaproductos.data.model.Accesorios
import com.example.appventaproductos.data.model.Carriola
import com.example.appventaproductos.data.model.Ropa
import com.example.appventaproductos.viewmodel.AccesoriosViewModel
import com.example.appventaproductos.viewmodel.CarriolaViewModel
import com.example.appventaproductos.viewmodel.RopaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(
    navController: NavHostController,
    initialCategory: String // "ropa" | "carriola" | "accesorios"
) {
    val ropaVM: RopaViewModel = viewModel()
    val carriolaVM: CarriolaViewModel = viewModel()
    val accesoriosVM: AccesoriosViewModel = viewModel()

    var nombre by rememberSaveable { mutableStateOf("") }
    var precio by rememberSaveable { mutableStateOf("") }
    var categoria by rememberSaveable { mutableStateOf(initialCategory) }
    var descripcion by rememberSaveable { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            imageUri = uri
        }
    )

    val defaultImage = R.drawable.unisex

    var showSavedDialog by remember { mutableStateOf(false) }
    var showNoChangeDialog by remember { mutableStateOf(false) }

    val categorias = listOf("ropa", "carriola", "accesorios")

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Nuevo producto") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Cancelar")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val valid = nombre.isNotBlank() && precio.isNotBlank()
                if (!valid) {
                    showNoChangeDialog = true
                    return@FloatingActionButton
                }

                val newId = when (categoria) {
                    "ropa" -> (ropaVM.ropa.value.maxOfOrNull { it.id } ?: 0) + 1
                    "carriola" -> (carriolaVM.carriola.value.maxOfOrNull { it.id } ?: 0) + 1
                    else -> (accesoriosVM.accesorios.value.maxOfOrNull { it.id } ?: 0) + 1
                }

                when (categoria) {
                    "ropa" -> ropaVM.addRopa(
                        Ropa(
                            id = newId,
                            imagen = defaultImage, // TODO: save image URI
                            TítuloProducto = nombre,
                            Precio = precio,
                            Condición = "Nuevo",
                            Características = descripcion,
                            Talla = "Única",
                            Materiales = "N/A",
                            Rangoedad = "N/A",
                            metodoEnvio = "Envío estándar"
                        )
                    )
                    "carriola" -> carriolaVM.addCarriola(
                        Carriola(
                            id = newId,
                            imagen = defaultImage, // TODO: save image URI
                            TítuloProducto = nombre,
                            Precio = precio,
                            Condición = "Nuevo",
                            Características = descripcion,
                            Peso = "N/A",
                            Materiales = "N/A",
                            Rangoedad = "N/A",
                            metodoEnvio = "Envío estándar"
                        )
                    )
                    "accesorios" -> accesoriosVM.addAccesorios(
                        Accesorios(
                            id = newId,
                            imagen = defaultImage, // TODO: save image URI
                            TítuloProducto = nombre,
                            Precio = precio,
                            Características = descripcion,
                            Materiales = "N/A",
                            Rangoedad = "N/A",
                            metodoEnvio = "Envío estándar"
                        )
                    )
                }
                showSavedDialog = true
            }) {
                Icon(Icons.Filled.Check, contentDescription = "Guardar producto")
            }
        }
    ) { inner ->
        Column(
            modifier = Modifier
                .padding(inner)
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre de producto") },
                modifier = Modifier.fillMaxWidth()
            )
            OutlinedTextField(
                value = precio,
                onValueChange = { precio = it },
                label = { Text("Precio") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                modifier = Modifier.fillMaxWidth()
            )

            var expanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded }
            ) {
                OutlinedTextField(
                    value = categoria,
                    onValueChange = {},
                    readOnly = true,
                    label = { Text("Categoría") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded)
                    },
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth()
                )
                ExposedDropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    categorias.forEach { c ->
                        DropdownMenuItem(
                            text = { Text(c) },
                            onClick = { categoria = c; expanded = false }
                        )
                    }
                }
            }

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción") },
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = 120.dp),
                singleLine = false,
                maxLines = 6
            )

            Text(text = "Añadir foto del producto")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = imageUri ?: defaultImage,
                    contentDescription = "Imagen de producto",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Button(
                onClick = { imagePicker.launch("image/*") },
                modifier = Modifier.align(Alignment.End)
            ) { Text("Añadir foto") }
        }
    }

    if (showSavedDialog) {
        AlertDialog(
            onDismissRequest = { showSavedDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    showSavedDialog = false
                    navController.popBackStack()
                }) { Text("OK") }
            },
            title = { Text("Producto guardado correctamente") },
            text = { Text("Tu producto se agregó a la categoría $categoria") }
        )
    }
    if (showNoChangeDialog) {
        AlertDialog(
            onDismissRequest = { showNoChangeDialog = false },
            confirmButton = { TextButton(onClick = { showNoChangeDialog = false }) { Text("OK") } },
            title = { Text("No se han guardado cambios") },
            text = { Text("Completa al menos Nombre y Precio para guardar") }
        )
    }
}