package com.example.appventaproductos.ui.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.compose.runtime.collectAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProductScreen(
    navController: NavHostController,
    category: String,
    id: Int
) {
    val ropaVM: RopaViewModel = viewModel(factory = RopaViewModel.Factory)
    val carriolaVM: CarriolaViewModel = viewModel(factory = CarriolaViewModel.Factory)
    val accesoriosVM: AccesoriosViewModel = viewModel(factory = AccesoriosViewModel.Factory)

    val originalCategory = remember(category) { category }

    val ropaState by ropaVM.getById(id).collectAsState(initial = null)
    val carriolaState by carriolaVM.getById(id).collectAsState(initial = null)
    val accesorioState by accesoriosVM.getById(id).collectAsState(initial = null)

    val currentItem = when (originalCategory) {
        "ropa" -> ropaState
        "carriola" -> carriolaState
        else -> accesorioState
    }

    var nombre by rememberSaveable { mutableStateOf("") }
    var precio by rememberSaveable { mutableStateOf("") }
    var categoria by rememberSaveable { mutableStateOf(originalCategory) }
    var descripcion by rememberSaveable { mutableStateOf("") }
    var imagenRes by rememberSaveable { mutableStateOf(0) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    var originalNombre by rememberSaveable { mutableStateOf("") }
    var originalPrecio by rememberSaveable { mutableStateOf("") }
    var originalDescripcion by rememberSaveable { mutableStateOf("") }
    LaunchedEffect(currentItem) {
        when (currentItem) {
            is Ropa -> {
                nombre = currentItem.TítuloProducto
                precio = currentItem.Precio
                descripcion = currentItem.Características
                categoria = "ropa"
                imagenRes = currentItem.imagen
                originalNombre = currentItem.TítuloProducto
                originalPrecio = currentItem.Precio
                originalDescripcion = currentItem.Características
            }
            is Carriola -> {
                nombre = currentItem.TítuloProducto
                precio = currentItem.Precio
                descripcion = currentItem.Características
                categoria = "carriola"
                imagenRes = currentItem.imagen
                originalNombre = currentItem.TítuloProducto
                originalPrecio = currentItem.Precio
                originalDescripcion = currentItem.Características
            }
            is Accesorios -> {
                nombre = currentItem.TítuloProducto
                precio = currentItem.Precio
                descripcion = currentItem.Características
                categoria = "accesorios"
                imagenRes = currentItem.imagen
                originalNombre = currentItem.TítuloProducto
                originalPrecio = currentItem.Precio
                originalDescripcion = currentItem.Características
            }
            else -> {
                nombre = ""
                precio = ""
                descripcion = ""
                categoria = originalCategory
                imagenRes = 0
                originalNombre = ""
                originalPrecio = ""
                originalDescripcion = ""
            }
        }
    }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            imageUri = uri
        }
    )

    var showSavedDialog by remember { mutableStateOf(false) }
    var showNoChangeDialog by remember { mutableStateOf(false) }
    var showDeletedDialog by remember { mutableStateOf(false) }
    var showDeleteConfirmation by remember { mutableStateOf(false) }

    val categorias = listOf("ropa", "carriola", "accesorios")

    val defaultImageByCategory = mapOf(
        "ropa" to R.drawable.unisex,
        "carriola" to R.drawable.carriola,
        "accesorios" to R.drawable.monitor
    )

    val displayedImage = imageUri ?: when {
        imagenRes != 0 -> imagenRes
        else -> defaultImageByCategory[categoria] ?: R.drawable.unisex
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Editar producto") },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Cancelar")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (currentItem == null) {
                        showNoChangeDialog = true
                        return@FloatingActionButton
                    }

                    val changed = nombre != originalNombre ||
                            precio != originalPrecio ||
                            descripcion != originalDescripcion ||
                            categoria != originalCategory ||
                            imageUri != null

                    if (!changed) {
                        showNoChangeDialog = true
                        return@FloatingActionButton
                    }

                    when (categoria) {
                        "ropa" -> {
                            val base = when (originalCategory) {
                                "ropa" -> currentItem as? Ropa
                                else -> null
                            }
                            val updated = (base ?: Ropa(
                                id = 0,
                                imagen = defaultImageByCategory["ropa"] ?: R.drawable.unisex,
                                TítuloProducto = nombre,
                                Precio = precio,
                                Condición = "Nuevo",
                                Características = descripcion,
                                Talla = "Única",
                                Materiales = "N/A",
                                Rangoedad = "N/A",
                                metodoEnvio = "Envío estándar"
                            )).copy(
                                id = if (originalCategory == "ropa") id else 0,
                                imagen = if (imagenRes != 0) imagenRes else defaultImageByCategory["ropa"] ?: R.drawable.unisex,
                                TítuloProducto = nombre,
                                Precio = precio,
                                Características = descripcion
                            )

                            if (originalCategory != "ropa") {
                                when (originalCategory) {
                                    "carriola" -> carriolaVM.removeById(id)
                                    "accesorios" -> accesoriosVM.removeById(id)
                                }
                                ropaVM.addRopa(updated.copy(id = 0))
                            } else {
                                ropaVM.updateRopa(updated)
                            }
                        }
                        "carriola" -> {
                            val base = when (originalCategory) {
                                "carriola" -> currentItem as? Carriola
                                else -> null
                            }
                            val updated = (base ?: Carriola(
                                id = 0,
                                imagen = defaultImageByCategory["carriola"] ?: R.drawable.carriola,
                                TítuloProducto = nombre,
                                Precio = precio,
                                Condición = "Nuevo",
                                Características = descripcion,
                                Peso = "N/A",
                                Materiales = "N/A",
                                Rangoedad = "N/A",
                                metodoEnvio = "Envío estándar"
                            )).copy(
                                id = if (originalCategory == "carriola") id else 0,
                                imagen = if (imagenRes != 0) imagenRes else defaultImageByCategory["carriola"] ?: R.drawable.carriola,
                                TítuloProducto = nombre,
                                Precio = precio,
                                Características = descripcion
                            )

                            if (originalCategory != "carriola") {
                                when (originalCategory) {
                                    "ropa" -> ropaVM.removeById(id)
                                    "accesorios" -> accesoriosVM.removeById(id)
                                }
                                carriolaVM.addCarriola(updated.copy(id = 0))
                            } else {
                                carriolaVM.updateCarriola(updated)
                            }
                        }
                        else -> {
                            val base = when (originalCategory) {
                                "accesorios" -> currentItem as? Accesorios
                                else -> null
                            }
                            val updated = base?.let {
                                Accesorios(
                                    id = it.id,
                                    imagen = if (imagenRes != 0) imagenRes else it.imagen,
                                    TítuloProducto = nombre,
                                    Precio = precio,
                                    Características = descripcion,
                                    Materiales = it.Materiales,
                                    Rangoedad = it.Rangoedad,
                                    metodoEnvio = it.metodoEnvio
                                )
                            } ?: Accesorios(
                                id = 0,
                                imagen = defaultImageByCategory["accesorios"] ?: R.drawable.monitor,
                                TítuloProducto = nombre,
                                Precio = precio,
                                Características = descripcion,
                                Materiales = "N/A",
                                Rangoedad = "N/A",
                                metodoEnvio = "Envío estándar"
                            )

                            if (originalCategory != "accesorios") {
                                when (originalCategory) {
                                    "ropa" -> ropaVM.removeById(id)
                                    "carriola" -> carriolaVM.removeById(id)
                                }
                                accesoriosVM.addAccesorios(updated.copy(id = 0))
                            } else {
                                accesoriosVM.updateAccesorios(updated)
                            }
                        }
                    }

                    showSavedDialog = true
                }
            ) {
                Icon(Icons.Filled.Check, contentDescription = "Guardar cambios")
            }
        }
    ) { inner ->
        if (currentItem == null) {
            Box(
                modifier = Modifier
                    .padding(inner)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text("No se encontró el producto")
            }
        } else {
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
                ExposedDropdownMenuBox(expanded = expanded, onExpandedChange = { expanded = !expanded }) {
                    OutlinedTextField(
                        value = categoria,
                        onValueChange = {},
                        readOnly = true,
                        label = { Text("Categoría") },
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                        modifier = Modifier
                            .menuAnchor()
                            .fillMaxWidth()
                    )
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        categorias.forEach { c ->
                            DropdownMenuItem(
                                text = { Text(c) },
                                onClick = {
                                    categoria = c
                                    expanded = false
                                }
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

                Text(text = "Foto del producto")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = displayedImage,
                        contentDescription = "Imagen de producto",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                }
                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = { imagePicker.launch("image/*") }) { Text("Cambiar") }
                    Button(
                        onClick = { showDeleteConfirmation = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer,
                            contentColor = MaterialTheme.colorScheme.onErrorContainer
                        )
                    ) { Text("Eliminar") }
                }

                Spacer(Modifier.height(16.dp))
            }
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
            title = { Text("Cambios guardados correctamente") },
            text = { Text("El producto ha sido actualizado") }
        )
    }
    if (showNoChangeDialog) {
        AlertDialog(
            onDismissRequest = { showNoChangeDialog = false },
            confirmButton = { TextButton(onClick = { showNoChangeDialog = false }) { Text("OK") } },
            title = { Text("No se han guardado cambios") },
            text = { Text("Edita algún campo antes de guardar") }
        )
    }
    if (showDeleteConfirmation) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            confirmButton = {
                TextButton(onClick = {
                    showDeleteConfirmation = false
                    when (originalCategory) {
                        "ropa" -> ropaVM.removeById(id)
                        "carriola" -> carriolaVM.removeById(id)
                        else -> accesoriosVM.removeById(id)
                    }
                    showDeletedDialog = true
                }) { Text("Eliminar") }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirmation = false }) { Text("Cancelar") }
            },
            title = { Text("¿Eliminar producto?") },
            text = { Text("Esta acción no se puede deshacer.") }
        )
    }
    if (showDeletedDialog) {
        AlertDialog(
            onDismissRequest = { showDeletedDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    showDeletedDialog = false
                    navController.popBackStack()
                }) { Text("OK") }
            },
            title = { Text("Producto eliminado") },
            text = { Text("Se eliminó el producto y volverás a la lista") }
        )
    }
}
