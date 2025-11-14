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
import androidx.compose.material3.DropdownMenu
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
import com.example.appventaproductos.data.model.Accesorio
import com.example.appventaproductos.data.model.Carriola
import com.example.appventaproductos.data.model.Ropa
import com.example.appventaproductos.viewmodel.AccesorioViewModel
import com.example.appventaproductos.viewmodel.CarriolaViewModel
import com.example.appventaproductos.viewmodel.RopaViewModel
import java.io.File

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProductScreen(
    navController: NavHostController,
    initialCategory: String // "ropa" | "carriola" | "accesorio"
) {
    val ropaVM: RopaViewModel = viewModel(factory = RopaViewModel.Factory)
    val carriolaVM: CarriolaViewModel = viewModel(factory = CarriolaViewModel.Factory)
    val accesorioVM: AccesorioViewModel = viewModel(factory = AccesorioViewModel.Factory)

    var nombre by rememberSaveable { mutableStateOf("") }
    var precio by rememberSaveable { mutableStateOf("") }
    var categoria by rememberSaveable { mutableStateOf(initialCategory) }
    var descripcion by rememberSaveable { mutableStateOf("") }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // Campos específicos por categoría
    var talla by rememberSaveable { mutableStateOf("") } // Para ropa
    var marca by rememberSaveable { mutableStateOf("") } // Para carriola
    var modelo by rememberSaveable { mutableStateOf("") } // Para carriola
    var tipo by rememberSaveable { mutableStateOf("") } // Para accesorio

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            imageUri = uri
        }
    )

    var showSavedDialog by remember { mutableStateOf(false) }
    var showNoChangeDialog by remember { mutableStateOf(false) }
    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val categorias = listOf("ropa", "carriola", "accesorio")

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

                val precioNumero = precio.toDoubleOrNull()
                if (precioNumero == null) {
                    errorMessage = "El precio debe ser un número válido"
                    showErrorDialog = true
                    return@FloatingActionButton
                }

                try {
                    when (categoria) {
                        "ropa" -> {
                            if (talla.isBlank()) {
                                errorMessage = "La talla es obligatoria para ropa"
                                showErrorDialog = true
                                return@FloatingActionButton
                            }
                            // TODO: Implementar subida de imagen
                            ropaVM.createRopa(
                                nombre = nombre,
                                talla = talla,
                                precio = precioNumero,
                                imagenFile = null // Por ahora sin imagen
                            )
                        }
                        "carriola" -> {
                            if (marca.isBlank() || modelo.isBlank()) {
                                errorMessage = "Marca y modelo son obligatorios para carriolas"
                                showErrorDialog = true
                                return@FloatingActionButton
                            }
                            carriolaVM.createCarriola(
                                marca = marca,
                                modelo = modelo,
                                precio = precioNumero,
                                imagenFile = null // Por ahora sin imagen
                            )
                        }
                        "accesorio" -> {
                            if (tipo.isBlank()) {
                                errorMessage = "El tipo es obligatorio para accesorios"
                                showErrorDialog = true
                                return@FloatingActionButton
                            }
                            val nuevoAccesorio = Accesorio(
                                id = null,
                                nombre = nombre,
                                tipo = tipo,
                                precio = precioNumero,
                                imagenUrl = null // Por ahora sin imagen
                            )
                            accesorioVM.createAccesorio(nuevoAccesorio)
                        }
                    }
                    showSavedDialog = true
                } catch (e: Exception) {
                    errorMessage = "Error al guardar: ${e.message}"
                    showErrorDialog = true
                }
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
            // Campo común: Nombre
            OutlinedTextField(
                value = nombre,
                onValueChange = { nombre = it },
                label = { Text("Nombre del producto") },
                modifier = Modifier.fillMaxWidth()
            )

            // Campo común: Precio
            OutlinedTextField(
                value = precio,
                onValueChange = { precio = it },
                label = { Text("Precio") },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal
                ),
                modifier = Modifier.fillMaxWidth()
            )

            // Selector de categoría
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
                DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                    categorias.forEach { c ->
                        DropdownMenuItem(
                            text = { Text(c.replaceFirstChar { it.uppercase() }) },
                            onClick = {
                                categoria = c
                                expanded = false
                            }
                        )
                    }
                }
            }

            // Campos específicos por categoría
            when (categoria) {
                "ropa" -> {
                    OutlinedTextField(
                        value = talla,
                        onValueChange = { talla = it },
                        label = { Text("Talla") },
                        placeholder = { Text("Ej: 0-3M, 3-6M, etc.") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                "carriola" -> {
                    OutlinedTextField(
                        value = marca,
                        onValueChange = { marca = it },
                        label = { Text("Marca") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    OutlinedTextField(
                        value = modelo,
                        onValueChange = { modelo = it },
                        label = { Text("Modelo") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                "accesorio" -> {
                    OutlinedTextField(
                        value = tipo,
                        onValueChange = { tipo = it },
                        label = { Text("Tipo") },
                        placeholder = { Text("Ej: Electrónico, Lactancia, etc.") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }

            // Campo común: Descripción
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

            // Sección de imagen
            Text(text = "Añadir foto del producto")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = imageUri ?: when (categoria) {
                        "ropa" -> R.drawable.unisex
                        "carriola" -> R.drawable.carriola
                        "accesorio" -> R.drawable.monitor
                        else -> R.drawable.unisex
                    },
                    contentDescription = "Imagen de producto",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }
            Button(
                onClick = { imagePicker.launch("image/*") },
                modifier = Modifier.align(Alignment.End)
            ) {
                Text("Añadir foto")
            }
        }
    }

    // Diálogos
    if (showSavedDialog) {
        AlertDialog(
            onDismissRequest = { showSavedDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    showSavedDialog = false
                    navController.popBackStack()
                }) {
                    Text("OK")
                }
            },
            title = { Text("Producto guardado correctamente") },
            text = { Text("Tu producto se agregó a la categoría ${categoria.replaceFirstChar { it.uppercase() }}") }
        )
    }

    if (showNoChangeDialog) {
        AlertDialog(
            onDismissRequest = { showNoChangeDialog = false },
            confirmButton = {
                TextButton(onClick = { showNoChangeDialog = false }) {
                    Text("OK")
                }
            },
            title = { Text("No se han guardado cambios") },
            text = { Text("Completa al menos Nombre y Precio para guardar") }
        )
    }

    if (showErrorDialog) {
        AlertDialog(
            onDismissRequest = { showErrorDialog = false },
            confirmButton = {
                TextButton(onClick = { showErrorDialog = false }) {
                    Text("OK")
                }
            },
            title = { Text("Error") },
            text = { Text(errorMessage) }
        )
    }
}