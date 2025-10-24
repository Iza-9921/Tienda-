package com.example.appventaproductos.ui.screens

import com.example.appventaproductos.data.model.Carriola
import com.example.appventaproductos.ui.theme.AppVentaProductosTheme
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.appventaproductos.R

@Composable
fun CarriolaScreen(carriola: Carriola, navController: NavController) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Imagen del producto
        Image(
            painter = painterResource(id = carriola.imagen),
            contentDescription = carriola.TítuloProducto,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Título
        Text(
            text = carriola.TítuloProducto,
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Precio
        Text(
            text = carriola.Precio,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Condición
        Text(
            text = "Condición: ${carriola.Condición}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Características
        Text(
            text = "Características:\n${carriola.Características}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Peso
        Text(
            text = "Peso: ${carriola.Peso}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Materiales
        Text(
            text = "Materiales: ${carriola.Materiales}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Rango de edad
        Text(
            text = "Rango de edad: ${carriola.Rangoedad}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Método de envío
        Text(
            text = "Método de envío: ${carriola.metodoenvio}",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botones
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = { navController.popBackStack() }) {
                Text("Volver a la lista")
            }

            Button(onClick = { /* Aquí podrías implementar "Agregar al carrito" */ }) {
                Text("Agregar al carrito")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCarriolaScreen() {
    val ejemplo = Carriola(
        id = 1,
        imagen = R.drawable.carriola, // Asegúrate de que exista en drawable
        TítuloProducto = "Carriola Premium 3 en 1",
        Precio = "MXN 8,999.00",
        Condición = "Nueva (Certificada)",
        Características = "Incluye moisés, asiento reversible y autoasiento.",
        Peso = "11.5 kg",
        Materiales = "Aluminio ligero, textiles hipoalergénicos",
        Rangoedad = "0 meses en adelante",
        metodoenvio = "Envío gratuito (3-5 días hábiles)"
    )

    AppVentaProductosTheme {
        CarriolaScreen(carriola = ejemplo, navController = rememberNavController())
    }
}

