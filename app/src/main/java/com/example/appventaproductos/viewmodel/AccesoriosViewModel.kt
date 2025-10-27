package com.example.appventaproductos.viewmodel

import androidx.lifecycle.ViewModel
import com.example.appventaproductos.R
import com.example.appventaproductos.data.model.Accesorios
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class AccesoriosViewModel : ViewModel() {

    private val _accesorios = MutableStateFlow<List<Accesorios>>(emptyList())
    val accesorios: StateFlow<List<Accesorios>> = _accesorios

    init {
        _accesorios.value = listOf(
            Accesorios(
                id = 1,
                imagen = R.drawable.monitor,
                TítuloProducto = "Monitor de Bebé Inteligente con Cámara HD y Visión Nocturna",
                Precio = "MXN 2,999.00",
                Características = "- Transmisión de video Full HD 1080p vía Wi-Fi a tu smartphone o tablet." +
                        "- Detección de movimiento, llanto y temperatura ambiente.",
                Materiales = "Plástico ABS resistente y componentes electrónicos",
                Rangoedad = "0 - 5 años",
                metodoEnvio = "Envío por servicio de mensajería (2 días hábiles) con seguro incluido"
            ),
            Accesorios(
                id = 2,
                imagen = R.drawable.extractor,
                TítuloProducto = "Extractor de Leche Eléctrico Doble Manos Libres con 5 Modos",
                Precio = "MXN 1,850.00",
                Características = "- Extracción doble para maximizar la producción de leche.\n" +
                        "    - 5 modos de succión y 9 niveles de intensidad ajustables.",
                Materiales = "Silicona de grado alimenticio y polipropileno (Libre de BPA",
                Rangoedad = "Para madres en periodo de lactancia",
                metodoEnvio = "Envío gratuito a domicilio (48 horas)"
            ),
            Accesorios(
                id = 3,
                imagen = R.drawable.colchoneta,
                TítuloProducto = "Manta de Actividades y Gimnasio para Bebé con Alfombra Sensorial",
                Precio = "MXN 980.00",
                Características = "\n" +
                        "    - Manta de gran tamaño con acolchado extra suave.\n" +
                        "    - Arcos flexibles con 5 juguetes colgantes desmontables (sonajeros, espejo seguro).",
                Materiales = "Tejido de algodón orgánico, poliéster y plástico no tóxico",
                Rangoedad = "0- 12 meses",
                metodoEnvio = "Entrega en punto de recogida (Pick Up) sin costo o envío estándar económico.\")"
            ),
            Accesorios(
                id = 4,
                imagen = R.drawable.portabebes,
                TítuloProducto = "Portabebés Ergonómico 4-en-1 de Lujo para Recién Nacidos y Niños",
                Precio = "MXN 1,599.00",
                Características = "\n" +
                        "    - Cuatro posiciones de transporte: frontal (mirando hacia adentro/afuera), cadera y espalda.\n" +
                        "    - Soporte lumbar acolchado para la comodidad del adulto.",
                Materiales = "Algodón transpirable de alta calidad y hebillas de seguridad reforzadas",
                Rangoedad = "3.5 kg hasta 20 kg",
                metodoEnvio = "Envío urgente al día siguiente (Next Day) por MXN 100.00"
            )
        )
    }

    fun getById(id: Int): Accesorios? = _accesorios.value.firstOrNull { it.id == id }

    fun addAccesorios(item: Accesorios) {
        _accesorios.update { it + item }
    }

    fun replaceById(id: Int, nuevo: Accesorios) {
        val lista = _accesorios.value.toMutableList()
        val index = lista.indexOfFirst { it.id == id }
        if (index != -1) {
            lista[index] = nuevo
            _accesorios.value = lista
        }
    }

    fun removeById(id: Int) {
        _accesorios.update { it.filterNot { a -> a.id == id } }
    }

    fun clickPerson(accesorios: Accesorios) {
        println("Has hecho click en: ${accesorios.TítuloProducto}")
    }
}
