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
import com.example.appventaproductos.data.model.Ropa
import com.example.appventaproductos.ui.theme.AppVentaProductosTheme


@Composable
fun RopaList(
    lista: List<Ropa>,
    onClick: (Ropa) -> Unit
) {
    LazyVerticalGrid(
        columns = GridCells.Adaptive(minSize = 220.dp),
        contentPadding = PaddingValues(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(items = lista, key = { it.id }) { ropa ->
            RopaCard(
                ropa = ropa,
                onClick = onClick
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
fun PreviewRopaBebeList() {
    val lista = listOf(
        Ropa(
            id = 1,
            imagen = R.drawable.body,
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

    AppVentaProductosTheme {
        Surface {
            RopaList(lista = lista, onClick = { /* acción de preview */ })
        }
    }
}