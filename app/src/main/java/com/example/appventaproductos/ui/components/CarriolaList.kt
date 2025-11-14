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
import com.example.appventaproductos.data.model.Carriola
import com.example.appventaproductos.ui.theme.AppVentaProductosTheme

@Composable
fun CarriolaList(
    lista: List<Carriola>,
    onClick: (Carriola) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 220.dp),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = lista, key = { it.id ?: 0 }) { carriola ->
            CarriolaCard(
                carriola = carriola,
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
            marca = "Carriola Modular Premium",
            modelo = "3-en-1 (Moises, Asiento Reversible)",
            precio = 8999.00,
            imagenUrl = null
        ),
        Carriola(
            id = 2,
            marca = "Carriola Ultra Ligera",
            modelo = "De Viaje Compacta",
            precio = 3450.00,
            imagenUrl = null
        ),
        Carriola(
            id = 3,
            marca = "Carriola Deportiva",
            modelo = "3 Ruedas Todo Terreno",
            precio = 6990.00,
            imagenUrl = null
        )
    )
    AppVentaProductosTheme {
        Surface {
            CarriolaList(lista = lista, onClick = { })
        }
    }
}