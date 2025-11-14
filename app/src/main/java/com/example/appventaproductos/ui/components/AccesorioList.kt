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
import com.example.appventaproductos.data.model.Accesorio
import com.example.appventaproductos.ui.theme.AppVentaProductosTheme

@Composable
fun AccesorioList(
    lista: List<Accesorio>,
    onClick: (Accesorio) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 220.dp),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = lista, key = { it.id ?: 0 }) { accesorio ->
            AccesorioCard(
                accesorio = accesorio,
                onClick = onClick
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
fun PreviewAccesorioList() {
    val lista = listOf(
        Accesorio(
            id = 1,
            nombre = "Monitor de Bebé Inteligente",
            tipo = "Electrónico",
            precio = 2999.00,
            imagenUrl = null
        ),
        Accesorio(
            id = 2,
            nombre = "Extractor de Leche Eléctrico",
            tipo = "Lactancia",
            precio = 1850.00,
            imagenUrl = null
        )
    )

    AppVentaProductosTheme {
        Surface {
            AccesorioList(lista = lista, onClick = { })
        }
    }
}