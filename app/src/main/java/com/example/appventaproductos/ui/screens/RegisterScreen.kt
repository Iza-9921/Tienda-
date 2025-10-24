package com.example.appventaproductos.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appventaproductos.R
import com.example.appventaproductos.ui.components.buttons.PrimaryButton
import com.example.appventaproductos.ui.components.images.CircularImage
import com.example.appventaproductos.ui.components.texts.Title

@Composable
fun RegisterScreen(navController: NavController) {
    // estados del formulario
    var nombre by remember { mutableStateOf("") }
    var apellido by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically)
    ) {
        CircularImage(R.drawable.descarga)
        Title("Registro")

        // Usuario
        TextField(
            value = nombre,
            onValueChange = {
                nombre = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nombre") },
            singleLine = true
        )
        TextField(
            value = apellido,
            onValueChange = {
                apellido = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Apellido") },
            singleLine = true
        )

        // Correo
        TextField(
            value = email,
            onValueChange = {
                email = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Correo electronico") },
            placeholder = { Text("ejemplo@correo.com") },
            singleLine = true
        )

        TextField(
            value = password,
            onValueChange = {
                password = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Contraseña") },
            singleLine = true
        )

        TextField(
            value = confirmPassword,
            onValueChange = {
                confirmPassword = it
            },
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Confirmar contraseña") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        PrimaryButton("Registrarse") {

        }
        Spacer(modifier = Modifier.height(8.dp))
        PrimaryButton("Volver al Login") {
            navController.popBackStack()
        }
    }
}

