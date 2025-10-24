package com.example.appventaproductos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.appventaproductos.R
import com.example.appventaproductos.data.model.Carriola
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CarriolaViewModel : ViewModel() {

    private val _carriola = MutableStateFlow<List<Carriola>>(emptyList())
    val carriola: StateFlow<List<Carriola>> = _carriola

    init {
      _carriola.value = listOf(
          Carriola(
               1,
               R.drawable.carriola,
               "Carriola Modular Premium 3-en-1 (Moises, Asiento Reversible y Autoasiento)",
              "MXN 8,999.00",
              "Nueva(Certificada y Sellada)",
              "- Ruedas de goma todo terreno con suspensión en las 4 ruedas.\n" +
                      "    - Amplia canasta de almacenamiento inferior." +
                      "- Incluye: Portavasos y cubrepiés.",
               "11.5 kg (Carriola con asiento)",
               "Chasis de aluminio ligero de alta resistencia y textiles hipoalergénicos",
              "0 Meses en adelante", // Corregido "Mese" → "Meses"
              "Envío terrestre gratuito a todo el país (3-5 días hábiles)"
          )
      )
    }
    fun clickPerson(carriola: Carriola){
        println("Has hecho click en : ${carriola.TítuloProducto}")

    }


}