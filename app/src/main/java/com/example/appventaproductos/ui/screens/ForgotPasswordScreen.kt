package com.example.appventaproductos.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.appventaproductos.R
import com.example.appventaproductos.ui.components.buttons.PrimaryButton
import com.example.appventaproductos.ui.components.images.CircularImage
import com.example.appventaproductos.ui.components.texts.Label
import com.example.appventaproductos.ui.components.texts.Title


@Composable
fun ForgotPasswordScreen(navController: NavController) {
    var email by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CircularImage(R.drawable.descarga)
        Title("Recuperar Contraseña")
        Spacer(modifier = Modifier.height(12.dp))
        Text("Ingrese su correo electronico")

        Spacer(modifier = Modifier.height(12.dp))
        TextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Correo electronico") },
            placeholder = { Text("ejemplo@correo.com") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        PrimaryButton("Enviar Enlace") { }

        Spacer(modifier = Modifier.height(16.dp))
        PrimaryButton("Volver al Login") {
            navController.popBackStack()
        }
    }

}