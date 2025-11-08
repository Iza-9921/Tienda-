package com.example.appventaproductos.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ropa")
data class RopaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imagen: Int,
    val titulo: String,
    val precio: String,
    val condicion: String,
    val caracteristicas: String,
    val talla: String,
    val materiales: String,
    val rangoEdad: String,
    val metodoEnvio: String
)

@Entity(tableName = "carriola")
data class CarriolaEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imagen: Int,
    val titulo: String,
    val precio: String,
    val condicion: String,
    val caracteristicas: String,
    val peso: String,
    val materiales: String,
    val rangoEdad: String,
    val metodoEnvio: String
)

@Entity(tableName = "accesorios")
data class AccesorioEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val imagen: Int,
    val titulo: String,
    val precio: String,
    val caracteristicas: String,
    val materiales: String,
    val rangoEdad: String,
    val metodoEnvio: String
)
