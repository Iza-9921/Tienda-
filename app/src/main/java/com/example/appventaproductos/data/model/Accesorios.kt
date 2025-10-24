package com.example.appventaproductos.data.model

import androidx.annotation.DrawableRes

class Accesorios (
    val id: Int,
    @DrawableRes val imagen: Int,
    val TítuloProducto: String,
    val Precio: String,
    val Características: String,
    val Materiales: String,
    val Rangoedad: String,
    val metodoEnvio: String,

    )