package com.example.appventaproductos.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appventaproductos.R
import com.example.appventaproductos.data.model.Carriola
import com.example.appventaproductos.ui.theme.AppVentaProductosTheme

@Composable
fun CarriolaList(lista: List<Carriola>, onClick: (Carriola) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(items = lista, key = { it.id }) { carriola ->
            CarriolaCard(carriola) { onClick(carriola) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLicList() {
    val lista = listOf(
        Carriola(
            id = 1,
            imagen = R.drawable.carriola,
            TítuloProducto = "Carriola Modular Premium 3-en-1 (Moises, Asiento Reversible y Autoasiento)",
            Precio = "MXN 8,999.00",
            Condición = "Nueva(Certificada y Sellada)",
            Características = "- Ruedas de goma todo terreno con suspensión en las 4 ruedas.\n" +
                    "    - Amplia canasta de almacenamiento inferior." +
                    "- Incluye: Portavasos y cubrepiés.",
            Peso = "11.5 kg (Carriola con asiento)",
            Materiales = "Chasis de aluminio ligero de alta resistencia y textiles hipoalergénicos",
            Rangoedad = "0 Meses en adelante", // Corregido "Mese" → "Meses"
            metodoEnvio = "Envío terrestre gratuito a todo el país (3-5 días hábiles)"
        ),
        Carriola( // AGREGADO: Segundo elemento para mejor preview
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

    AppVentaProductosTheme {
        CarriolaList(
            lista = lista, onClick = {  }
        )
    }
}