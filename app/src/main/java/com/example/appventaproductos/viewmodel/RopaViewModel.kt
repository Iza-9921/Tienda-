package com.example.appventaproductos.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appventaproductos.data.model.Ropa
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import com.example.appventaproductos.R

class RopaViewModel : ViewModel() {

    private val _ropa = MutableStateFlow<List<Ropa>>(emptyList())
    val ropa: StateFlow<List<Ropa>> = _ropa

    init {

        _ropa.value = listOf(
            Ropa(
                id = 1,
                imagen = R.drawable.unisex,
                TítuloProducto = "Body algodón manga larga - Rosa con ositos",
                Precio = "MXN 199.00",
                Condición = "Nuevo",
                Características = "Botones de presión en la entrepierna, costuras suaves, estampado resistente al lavado.",
                Talla = "0-3 M",
                Materiales = "Algodón 100% (peinado, hipoalergénico)",
                Rangoedad = "0 - 3 meses",
                metodoEnvio = "Envío a domicilio (Paquetería) — 3-5 días hábiles"
            ),
            Ropa(
                id = 2,
                imagen = R.drawable.pijama,
                TítuloProducto = "Set pijama 2 piezas - Estrellas",
                Precio = "MXN 349.00",
                Condición = "Nuevo",
                Características = "Incluye pijama y gorro. Cierre con broches, tela suave para dormir.",
                Talla = "3-6 M",
                Materiales = "Algodón peinado 95% / Elastano 5%",
                Rangoedad = "3 - 6 meses",
                metodoEnvio = "Recoger en tienda / Envío estándar"
            ),
            Ropa(
                id = 3,
                imagen = R.drawable.unisex,
                TítuloProducto = "Enterizo sin mangas unisex - Beige",
                Precio = "MXN 259.00",
                Condición = "Nuevo",
                Características = "Apertura inferior con broches, tejido transpirable.",
                Talla = "6-9 M",
                Materiales = "Algodón orgánico 100%",
                Rangoedad = "6 - 9 meses",
                metodoEnvio = "Envío a domicilio (Paquetería)"
            ),
            Ropa(
                id = 4,
                imagen = R.drawable.sudadera,
                TítuloProducto = "Sudadera con capucha 'Osito' - Gris",
                Precio = "MXN 429.00",
                Condición = "Nuevo",
                Características = "Forro polar ligero, capucha con orejitas, cierre frontal.",
                Talla = "9-12 M",
                Materiales = "Poliéster 80% / Algodón 20%",
                Rangoedad = "9 - 12 meses",
                metodoEnvio = "Envío express disponible"
            )

        )
    }

    fun getById(id: Int): Ropa? = _ropa.value.firstOrNull { it.id == id }

    fun addRopa(item: Ropa) {
        _ropa.update { it + item }
    }

    fun removeById(id: Int) {
        _ropa.update { it.filterNot { r -> r.id == id } }
    }

    fun clickRopa(ropa: Ropa) {
        println("Has hecho click en: ${ropa.TítuloProducto}")
    }
}