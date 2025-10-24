package com.example.appventaproductos.data.model

import androidx.annotation.DrawableRes
import com.example.appventaproductos.R

data class Carriola(
    val id: Int,
    @DrawableRes val imagen: Int,
    val TítuloProducto: String,
    val Precio: String,
    val Condición: String,
    val Características: String,
    val Peso: String,
    val Materiales: String,
    val Rangoedad: String,
    val metodoEnvio: String,

    )