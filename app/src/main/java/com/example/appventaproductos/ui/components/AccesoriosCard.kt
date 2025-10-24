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
import com.example.appventaproductos.data.model.Accesorios

@Composable
fun AccesoriosCard(
    accesorios: Accesorios,
    onClick: (Accesorios) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ElevatedCard(
        onClick = { onClick(accesorios) },
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(3f / 4f)
    ) {
        Column(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = accesorios.imagen),
                contentDescription = accesorios.TítuloProducto,
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
                    text = accesorios.TítuloProducto,
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
                        text = accesorios.Precio,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                }

                if (accesorios.Características.isNotBlank()) {
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = accesorios.Características,
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
                        text = accesorios.Rangoedad,
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
                        text = "Materiales: ${accesorios.Materiales}",
                        style = MaterialTheme.typography.bodySmall,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                    Spacer(Modifier.height(4.dp))
                    Text(
                        text = "Envío: ${accesorios.metodoEnvio}",
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
fun PreviewAccesoriosCard() {
    val sample = Accesorios(
        id = 1,
        imagen = R.drawable.monitor,
        TítuloProducto = "Monitor de Bebé Inteligente con Cámara HD y Visión Nocturna",
        Precio = "MXN 2,999.00",
        Características = "- Transmisión de video Full HD 1080p vía Wi-Fi a tu smartphone o tablet.\n" +
                "    - Detección de movimiento, llanto y temperatura ambiente.",
        Materiales = "Plástico ABS resistente y componentes electrónicos",
        Rangoedad = "0 - 5 años",
        metodoEnvio = "Envío por servicio de mensajería (2 días hábiles) con seguro incluido."
    )

    AppVentaProductosTheme {
        AccesoriosCard(accesorios = sample, onClick = { /* acción de preview */ })
    }
}