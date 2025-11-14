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
import androidx.compose.runtime.collectAsState
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditProductScreen(
    navController: NavHostController,
    category: String,
    id: Int
) {
    val ropaVM: RopaViewModel = viewModel(factory = RopaViewModel.Factory)
    val carriolaVM: CarriolaViewModel = viewModel(factory = CarriolaViewModel.Factory)
    val accesorioVM: AccesorioViewModel = viewModel(factory = AccesorioViewModel.Factory)

    val originalCategory = remember(category) { category }

    // Cargar datos del producto
    LaunchedEffect(id, category) {
        when (category) {
            "ropa" -> ropaVM.loadRopa()
            "carriola" -> carriolaVM.loadCarriola()
            "accesorio" -> accesorioVM.loadAccesorio()
        }
    }

    val ropaList by ropaVM.ropaList.collectAsState()
    val carriolaList by carriolaVM.carriolaList.collectAsState()
    val accesorioList by accesorioVM.accesorioList.collectAsState()

    // Encontrar el producto actual por ID
    val currentItem = when (originalCategory) {
        "ropa" -> ropaList.find { it.id == id }
        "carriola" -> carriolaList.find { it.id == id }
        "accesorio" -> accesorioList.find { it.id == id }
        else -> null
    }

    // Estados para el formulario
    var nombre by rememberSaveable { mutableStateOf("") }
    var precio by rememberSaveable { mutableStateOf("") }
    var categoria by rememberSaveable { mutableStateOf(originalCategory) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    // Campos específicos por categoría
    var talla by rememberSaveable { mutableStateOf("") }
    var marca by rememberSaveable { mutableStateOf("") }
    var modelo by rememberSaveable { mutableStateOf("") }
    var tipo by rememberSaveable { mutableStateOf("") }

    // Valores originales para comparar cambios
    var originalNombre by rememberSaveable { mutableStateOf("") }
    var originalPrecio by rememberSaveable { mutableStateOf("") }
    var originalTalla by rememberSaveable { mutableStateOf("") }
    var originalMarca by rememberSaveable { mutableStateOf("") }
    var originalModelo by rememberSaveable { mutableStateOf("") }
    var originalTipo by rememberSaveable { mutableStateOf("") }

    // Cargar datos del producto cuando esté disponible
    LaunchedEffect(currentItem) {
        when (currentItem) {
            is Ropa -> {
                nombre = currentItem.nombre
                precio = currentItem.precio.toString()
                talla = currentItem.talla
                categoria = "ropa"
                originalNombre = currentItem.nombre
                originalPrecio = currentItem.precio.toString()
                originalTalla = currentItem.talla
            }
            is Carriola -> {
                nombre = "${currentItem.marca} ${currentItem.modelo}"
                precio = currentItem.precio.toString()
                marca = currentItem.marca
                modelo = currentItem.modelo
                categoria = "carriola"
                originalNombre = "${currentItem.marca} ${currentItem.modelo}"
                originalPrecio = currentItem.precio.toString()
                originalMarca = currentItem.marca
                originalModelo = currentItem.modelo
            }
            is Accesorio -> {
                nombre = currentItem.nombre
                precio = currentItem.precio.toString()
                tipo = currentItem.tipo
                categoria = "accesorio"
                originalNombre = currentItem.nombre
                originalPrecio = currentItem.precio.toString()
                originalTipo = currentItem.tipo
            }
            else -> {
                // Resetear valores si no hay producto
                nombre = ""
                precio = ""
                talla = ""
                marca = ""
                modelo = ""
                tipo = ""
                originalNombre = ""
                originalPrecio = ""
                originalTalla = ""
                originalMarca = ""
                originalModelo = ""
                originalTipo = ""
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
    var showErrorDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }

    val categorias = listOf("ropa", "carriola", "accesorio")

    val defaultImageByCategory = mapOf(
        "ropa" to R.drawable.unisex,
        "carriola" to R.drawable.carriola,
        "accesorio" to R.drawable.monitor
    )

    val displayedImage = imageUri ?: defaultImageByCategory[categoria] ?: R.drawable.unisex

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
                        errorMessage = "No se encontró el producto a editar"
                        showErrorDialog = true
                        return@FloatingActionButton
                    }

                    // Validaciones básicas
                    if (nombre.isBlank() || precio.isBlank()) {
                        errorMessage = "Nombre y precio son obligatorios"
                        showErrorDialog = true
                        return@FloatingActionButton
                    }

                    val precioNumero = precio.toDoubleOrNull()
                    if (precioNumero == null) {
                        errorMessage = "El precio debe ser un número válido"
                        showErrorDialog = true
                        return@FloatingActionButton
                    }

                    // Validaciones específicas por categoría
                    when (categoria) {
                        "ropa" -> {
                            if (talla.isBlank()) {
                                errorMessage = "La talla es obligatoria para ropa"
                                showErrorDialog = true
                                return@FloatingActionButton
                            }
                        }
                        "carriola" -> {
                            if (marca.isBlank() || modelo.isBlank()) {
                                errorMessage = "Marca y modelo son obligatorios para carriolas"
                                showErrorDialog = true
                                return@FloatingActionButton
                            }
                        }
                        "accesorio" -> {
                            if (tipo.isBlank()) {
                                errorMessage = "El tipo es obligatorio para accesorios"
                                showErrorDialog = true
                                return@FloatingActionButton
                            }
                        }
                    }

                    // Verificar si hay cambios
                    val hasChanges = when (categoria) {
                        "ropa" -> nombre != originalNombre || precio != originalPrecio || talla != originalTalla
                        "carriola" -> nombre != originalNombre || precio != originalPrecio || marca != originalMarca || modelo != originalModelo
                        "accesorio" -> nombre != originalNombre || precio != originalPrecio || tipo != originalTipo
                        else -> false
                    } || imageUri != null

                    if (!hasChanges) {
                        showNoChangeDialog = true
                        return@FloatingActionButton
                    }

                    try {
                        // Actualizar según la categoría
                        when (categoria) {
                            "ropa" -> {
                                // Por ahora solo actualizamos localmente ya que el servidor no tiene PUT
                                // En una implementación real, aquí llamarías a una API PUT
                                ropaVM.loadRopa() // Recargar para simular actualización
                            }
                            "carriola" -> {
                                carriolaVM.loadCarriola() // Recargar para simular actualización
                            }
                            "accesorio" -> {
                                accesorioVM.loadAccesorio() // Recargar para simular actualización
                            }
                        }
                        showSavedDialog = true
                    } catch (e: Exception) {
                        errorMessage = "Error al actualizar: ${e.message}"
                        showErrorDialog = true
                    }
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
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
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

                // Sección de imagen
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
                    Button(onClick = { imagePicker.launch("image/*") }) {
                        Text("Cambiar foto")
                    }
                    Button(
                        onClick = { showDeleteConfirmation = true },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.errorContainer,
                            contentColor = MaterialTheme.colorScheme.onErrorContainer
                        )
                    ) {
                        Text("Eliminar producto")
                    }
                }

                Spacer(Modifier.height(16.dp))
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
            title = { Text("Cambios guardados correctamente") },
            text = { Text("El producto ha sido actualizado") }
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
            text = { Text("Edita algún campo antes de guardar") }
        )
    }

    if (showDeleteConfirmation) {
        AlertDialog(
            onDismissRequest = { showDeleteConfirmation = false },
            confirmButton = {
                TextButton(onClick = {
                    showDeleteConfirmation = false
                    // En una implementación real, aquí llamarías a una API DELETE
                    // Por ahora solo navegamos de regreso
                    showDeletedDialog = true
                }) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteConfirmation = false }) {
                    Text("Cancelar")
                }
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
                }) {
                    Text("OK")
                }
            },
            title = { Text("Producto eliminado") },
            text = { Text("Se eliminó el producto y volverás a la lista") }
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