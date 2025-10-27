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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
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
fun EditProductScreen(
    navController: NavHostController,
    category: String,
    id: Int
) {
    val ropaVM: RopaViewModel = viewModel()
    val carriolaVM: CarriolaViewModel = viewModel()
    val accesoriosVM: AccesoriosViewModel = viewModel()

    val currentNombre: String
    val currentPrecio: String
    val currentDescripcion: String
    val currentImg: Any

    when (category) {
        "ropa" -> {
            val item = ropaVM.getById(id)
            currentNombre = item?.TítuloProducto ?: ""
            currentPrecio = item?.Precio ?: ""
            currentDescripcion = item?.Características ?: ""
            currentImg = item?.imagen ?: R.drawable.unisex
        }
        "carriola" -> {
            val item = carriolaVM.getById(id)
            currentNombre = item?.TítuloProducto ?: ""
            currentPrecio = item?.Precio ?: ""
            currentDescripcion = item?.Características ?: ""
            currentImg = item?.imagen ?: R.drawable.carriola
        }
        else -> {
            val item = accesoriosVM.getById(id)
            currentNombre = item?.TítuloProducto ?: ""
            currentPrecio = item?.Precio ?: ""
            currentDescripcion = item?.Características ?: ""
            currentImg = item?.imagen ?: R.drawable.monitor
        }
    }

    var nombre by rememberSaveable { mutableStateOf(currentNombre) }
    var precio by rememberSaveable { mutableStateOf(currentPrecio) }
    var categoria by rememberSaveable { mutableStateOf(category) }
    var descripcion by rememberSaveable { mutableStateOf(currentDescripcion) }
    var imageUri by remember { mutableStateOf<Uri?>(null) }

    val imagePicker = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            imageUri = uri
        }
    )

    var showSavedDialog by remember { mutableStateOf(false) }
    var showNoChangeDialog by remember { mutableStateOf(false) }
    var showDeletedDialog by remember { mutableStateOf(false) }

    val categorias = listOf("ropa", "carriola", "accesorios")

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
            FloatingActionButton(onClick = {
                val changed = nombre != currentNombre ||
                        precio != currentPrecio ||
                        descripcion != currentDescripcion ||
                        categoria != category ||
                        imageUri != null

                if (!changed) {
                    showNoChangeDialog = true
                    return@FloatingActionButton
                }

                when (categoria) {
                    "ropa" -> {
                        val nuevo = Ropa(
                            id = id,
                            imagen = currentImg as Int, // TODO: save image URI
                            TítuloProducto = nombre,
                            Precio = precio,
                            Condición = "Nuevo",
                            Características = descripcion,
                            Talla = "Única",
                            Materiales = "N/A",
                            Rangoedad = "N/A",
                            metodoEnvio = "Envío estándar"
                        )
                        if (category != "ropa") {
                            when (category) {
                                "carriola" -> carriolaVM.removeById(id)
                                "accesorios" -> accesoriosVM.removeById(id)
                            }
                            ropaVM.addRopa(nuevo)
                        } else {
                            ropaVM.replaceById(id, nuevo)
                        }
                    }
                    "carriola" -> {
                        val nuevo = Carriola(
                            id = id,
                            imagen = currentImg as Int, // TODO: save image URI
                            TítuloProducto = nombre,
                            Precio = precio,
                            Condición = "Nuevo",
                            Características = descripcion,
                            Peso = "N/A",
                            Materiales = "N/A",
                            Rangoedad = "N/A",
                            metodoEnvio = "Envío estándar"
                        )
                        if (category != "carriola") {
                            when (category) {
                                "ropa" -> ropaVM.removeById(id)
                                "accesorios" -> accesoriosVM.removeById(id)
                            }
                            carriolaVM.addCarriola(nuevo)
                        } else {
                            carriolaVM.replaceById(id, nuevo)
                        }
                    }
                    else -> {
                        val nuevo = Accesorios(
                            id = id,
                            imagen = currentImg as Int, // TODO: save image URI
                            TítuloProducto = nombre,
                            Precio = precio,
                            Características = descripcion,
                            Materiales = "N/A",
                            Rangoedad = "N/A",
                            metodoEnvio = "Envío estándar"
                        )
                        if (category != "accesorios") {
                            when (category) {
                                "ropa" -> ropaVM.removeById(id)
                                "carriola" -> carriolaVM.removeById(id)
                            }
                            accesoriosVM.addAccesorios(nuevo)
                        } else {
                            accesoriosVM.replaceById(id, nuevo)
                        }
                    }
                }

                showSavedDialog = true
            }) {
                Icon(Icons.Filled.Check, contentDescription = "Guardar cambios")
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

            Text(text = "Foto del producto")
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                contentAlignment = Alignment.Center
            ) {
                AsyncImage(
                    model = imageUri ?: currentImg,
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
                OutlinedButton(
                    onClick = {
                        when (category) {
                            "ropa" -> ropaVM.removeById(id)
                            "carriola" -> carriolaVM.removeById(id)
                            else -> accesoriosVM.removeById(id)
                        }
                        showDeletedDialog = true
                    }
                ) { Text("Eliminar") }
            }

            Spacer(Modifier.height(16.dp))
            Button(
                onClick = {
                    when (category) {
                        "ropa" -> ropaVM.removeById(id)
                        "carriola" -> carriolaVM.removeById(id)
                        else -> accesoriosVM.removeById(id)
                    }
                    showDeletedDialog = true
                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.errorContainer,
                    contentColor = MaterialTheme.colorScheme.onErrorContainer
                )
            ) { Text("Eliminar producto") }
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
    if (showDeletedDialog) {
        AlertDialog(
            onDismissRequest = { showDeletedDialog = false },
            confirmButton = {
                TextButton(onClick = {
                    showDeletedDialog = false
                    navController.popBackStack()
                }) { Text("OK") }
            },
            title = { Text("Producto eliminado correctamente") },
            text = { Text("Se eliminó el producto y volverás a la lista") }
        )
    }
}
