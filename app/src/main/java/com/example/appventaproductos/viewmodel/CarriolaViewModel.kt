package com.example.appventaproductos.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appventaproductos.R
import com.example.appventaproductos.data.model.Carriola
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CarriolaViewModel : ViewModel() {

    private val _carriola = MutableStateFlow<List<Carriola>>(emptyList())
    val carriola: StateFlow<List<Carriola>> = _carriola

    init {
        _carriola.value = listOf(
            Carriola(
                id = 1,
                imagen = R.drawable.carriola,
                TítuloProducto = "Carriola Modular Premium 3-en-1 (Moises, Asiento Reversible y Autoasiento)",
                Precio = "MXN 8,999.00",
                Condición = "Nueva (Certificada y Sellada)",
                Características = "- Ruedas de goma todo terreno con suspensión en las 4 ruedas.\n" +
                        " - Amplia canasta de almacenamiento inferior.\n" +
                        " - Incluye: Portavasos y cubrepiés.",
                Peso = "11.5 kg (Carriola con asiento)",
                Materiales = "Chasis de aluminio ligero de alta resistencia y textiles hipoalergénicos",
                Rangoedad = "0 meses en adelante",
                metodoEnvio = "Envío terrestre gratuito a todo el país (3-5 días hábiles)"
            ),
            Carriola(
                id = 2,
                imagen = R.drawable.carriola1,
                TítuloProducto = "Carriola Ultra Ligera de Viaje",
                Precio = "MXN 3,450.00",
                Condición = "Nueva (Certificada por fabricante)",
                Características = "- Diseño ultra compacto, apto para cabina de avión.\n- Peso pluma, fácil de transportar.",
                Peso = "2.6 kg",
                Materiales = "Marco de acero reforzado",
                Rangoedad = "A partir de los 6 meses",
                metodoEnvio = "Envío Express (24-48 horas)"
            )
        )
    }

    fun getById(id: Int): Carriola? = _carriola.value.firstOrNull { it.id == id }

    fun addCarriola(item: Carriola) {
        _carriola.update { it + item }
    }

    fun replaceById(id: Int, nuevo: Carriola) {
        val lista = _carriola.value.toMutableList()
        val index = lista.indexOfFirst { it.id == id }
        if (index != -1) {
            lista[index] = nuevo
            _carriola.value = lista
        }
    }

    fun removeById(id: Int) {
        _carriola.update { it.filterNot { c -> c.id == id } }
    }

    fun clickPerson(carriola: Carriola) {
        println("Has hecho click en: ${carriola.TítuloProducto}")
    }
}
