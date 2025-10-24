package com.example.appventaproductos.ui.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.tooling.preview.Preview
import com.example.appventaproductos.R
import com.example.appventaproductos.data.model.Accesorios
import com.example.appventaproductos.data.model.Ropa
import com.example.appventaproductos.ui.theme.AppVentaProductosTheme


@Composable
fun AccesoriosList(
    lista: List<Accesorios>,
    onClick: (Accesorios) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 220.dp),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = lista, key = { it.id }) { accesorios ->
            AccesoriosCard(
                accesorios = accesorios,
                onClick = onClick
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
fun PreviewAccesoriosList() {
    val lista = listOf(
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

    AppVentaProductosTheme {
        Surface {
            AccesoriosList(lista = lista, onClick = { /* acción de preview */ })
        }
    }
}