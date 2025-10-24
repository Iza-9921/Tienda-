package com.example.appventaproductos.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AssistChip
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.Hyphens
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appventaproductos.data.model.Ropa
import com.example.appventaproductos.ui.theme.AppVentaProductosTheme
import com.example.appventaproductos.R

@Composable
fun RopaCard(
    ropa: Ropa,
    onClick: (Ropa) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ElevatedCard(
        onClick = { onClick(ropa) },
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(3f / 4f)
    ) {
        Column(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = ropa.imagen),
                contentDescription = ropa.TítuloProducto,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(12.dp)
            ) {
                Text(
                    text = ropa.TítuloProducto,
                    style = MaterialTheme.typography.titleSmall.copy(
                        lineBreak = LineBreak.Paragraph,
                        hyphens = Hyphens.Auto
                    ),
                    maxLines = if (expanded) Int.MAX_VALUE else 3,
                    overflow = TextOverflow.Ellipsis
                )

                Spacer(Modifier.height(6.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = ropa.Precio,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    AssistChip(
                        onClick = { /* informativo */ },
                        label = {
                            Text(
                                ropa.Condición,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }

                if (ropa.Características.isNotBlank()) {
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = ropa.Características,
                        style = MaterialTheme.typography.bodySmall.copy(
                            lineBreak = LineBreak.Paragraph,
                            hyphens = Hyphens.Auto
                        ),
                        maxLines = if (expanded) Int.MAX_VALUE else 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(Modifier.height(6.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Talla: ${ropa.Talla}",
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = ropa.Rangoedad,
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    TextButton(onClick = { expanded = !expanded }) {
                        Text(if (expanded) "Ver menos" else "Ver más")
                    }
                }

                if (expanded) {
                    Spacer(Modifier.height(8.dp))
                    Text(
                        text = "Materiales: ${ropa.Materiales}",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Envío: ${ropa.metodoEnvio}",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
fun PreviewRopaCard() {
    val sample = Ropa(
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
    )

    AppVentaProductosTheme {
        RopaCard(ropa = sample, onClick = { /* acción de preview */ })
    }
}