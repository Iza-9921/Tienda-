package com.example.appventaproductos.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "carriola")
data class Carriola(
    @PrimaryKey
    val id: Int? = null,
    val marca: String,
    val modelo: String,
    val precio: Double,
    @SerializedName("imagen_url")
    val imagenUrl: String?
)