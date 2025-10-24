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
import com.example.appventaproductos.R
import com.example.appventaproductos.data.model.Carriola
import com.example.appventaproductos.ui.theme.AppVentaProductosTheme

@Composable
fun CarriolaCard(
    Car: Carriola,
    onClick: (Carriola) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ElevatedCard(
        onClick = { onClick(Car) },
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(3f / 4f)
    ) {
        Column(Modifier.fillMaxSize()) {
            Image(
                painter = painterResource(id = Car.imagen),
                contentDescription = Car.TítuloProducto,
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
                    text = Car.TítuloProducto,
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
                        text = Car.Precio,
                        style = MaterialTheme.typography.titleMedium,
                        fontWeight = FontWeight.SemiBold
                    )
                    AssistChip(
                        onClick = { /* informativo */ },
                        label = {
                            Text(
                                Car.Condición,
                                maxLines = 1,
                                overflow = TextOverflow.Ellipsis
                            )
                        }
                    )
                }

                if (Car.Características.isNotBlank()) {
                    Spacer(Modifier.height(6.dp))
                    Text(
                        text = Car.Características,
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
                        text = Car.Peso,
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = Car.Rangoedad,
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    TextButton(onClick = { expanded = !expanded }) {
                        Text(if (expanded) "Ver menos" else "Ver más")
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFFFFFFFF, widthDp = 360)
@Composable
fun PreviewCarriolaCard() {
    val c = Carriola(
        id = 1,
        imagen = R.drawable.carriola,
        TítuloProducto = "Carriola Modular Premium 3-en-1 (Moises, Asiento Reversible y Autoasiento)",
        Precio = "MXN 8,999.00",
        Condición = "Nueva (Certificada y Sellada)",
        Características = "- Ruedas de goma todo terreno con suspensión en las 4 ruedas.\n" +
                " - Amplia canasta de almacenamiento inferior.\n" +
                " - Incluye: Portavasos y cubrepiés.",
        Peso = "11.5 kg",
        Materiales = "Chasis de aluminio ligero y textiles hipoalergénicos",
        Rangoedad = "0 meses en adelante",
        metodoEnvio = "Envío terrestre gratuito (3-5 días hábiles)"
    )
    AppVentaProductosTheme { CarriolaCard(Car = c, onClick = { }) }
}
