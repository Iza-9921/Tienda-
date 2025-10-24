package com.example.appventaproductos.data.model

import androidx.annotation.DrawableRes

data class Ropa(
    val id: Int,
    @DrawableRes val imagen: Int,
    val TítuloProducto: String,
    val Precio: String,
    val Condición: String,
    val Características: String,
    val Talla: String,
    val Materiales: String,
    val Rangoedad: String,
    val metodoEnvio: String,
)
