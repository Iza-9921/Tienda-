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
import com.example.appventaproductos.data.model.Carriola
import com.example.appventaproductos.ui.theme.AppVentaProductosTheme

@Composable
fun CarriolaList(
    lista: List<Carriola>,
    onClick: (Carriola) -> Unit
) {
    LazyVerticalGrid(
        // Ajusta este valor: 220.dp hace cada tarjeta más ancha; usa Fixed(1) para 1 columna
        columns = GridCells.Adaptive(minSize = 220.dp),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = lista, key = { it.id }) { carriola ->
            CarriolaCard(
                Car = carriola,
                onClick = onClick
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
fun PreviewCarriolaList() {
    val lista = listOf(
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
            imagen = R.drawable.carriola,
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
    AppVentaProductosTheme { Surface { CarriolaList(lista = lista, onClick = { }) } }
}
