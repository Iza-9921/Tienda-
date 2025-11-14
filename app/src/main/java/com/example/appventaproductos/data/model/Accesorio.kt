package com.example.appventaproductos.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "accesorio")
data class Accesorio(
    @PrimaryKey
    val id: Int? = null,
    val nombre: String,
    val tipo: String,
    val precio: Double,
    @SerializedName("imagen_url")
    val imagenUrl: String?
)