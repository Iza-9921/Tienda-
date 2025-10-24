package com.example.appventaproductos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appventaproductos.R
import com.example.appventaproductos.data.model.Carriola
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CarriolasViewModel : ViewModel() {

    // Estado interno (mutable)
    private val _carriolas = MutableStateFlow<List<Carriola>>(emptyList())

    // Estado público (solo lectura)
    val carriolas: StateFlow<List<Carriola>> = _carriolas

    init {
        // Simulación de carga inicial
        Carriolas()
    }

    private fun Carriolas() {
        viewModelScope.launch {
            // Aquí podrías hacer una llamada a base de datos o API
            val listaEjemplo = listOf(
                Carriola(
                    1,
                    R.drawable.carriola,
                    "Carriola Modular Premium 3-en-1",
                    "MXN 8,999.00",
                    "Nueva (Certificada y Sellada)",
                    "- Ruedas todo terreno con suspensión.\n- Amplia canasta de almacenamiento inferior.\n- Incluye portavasos y cubrepiés.",
                    "11.5 kg",
                    "Aluminio ligero y textiles hipoalergénicos",
                    "0 meses en adelante",
                    "Envío gratuito a todo México"
                ),
                Carriola(
                    2,
                    R.drawable.carriola,
                    "Carriola Ultra Ligera de Viaje",
                    "MXN 3,450.00",
                    "Nueva (Certificada por fabricante)",
                    "- Diseño compacto, apto para cabina de avión.\n- Respaldo reclinable 150°.\n- Freno de pie de un toque.",
                    "2.6 kg",
                    "Marco de acero reforzado",
                    "Desde 6 meses",
                    "Envío express (24-48 hrs)"
                ),
                Carriola(
                    3,
                    R.drawable.carriola,
                    "Carriola Deportiva Jogger 3 Ruedas",
                    "MXN 6,999.00",
                    "Nueva (Ideal para padres activos)",
                    "- Freno de mano y suspensión ajustable.\n- Ruedas de aire todo terreno.",
                    "13.8 kg",
                    "Aluminio aeronáutico",
                    "Desde 6 meses",
                    "Recolección local o envío nacional"
                )
            )

            _carriolas.value = listaEjemplo
        }
    }

    // Si quieres actualizar la lista dinámicamente
    fun agregarCarriola(nueva: Carriola) {
        val listaActual = _carriolas.value.toMutableList()
        listaActual.add(nueva)
        _carriolas.value = listaActual
    }

    fun eliminarCarriola(id: Int) {
        _carriolas.value = _carriolas.value.filterNot { it.id == id }
    }
}
