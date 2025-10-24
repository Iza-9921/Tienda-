package com.example.appventaproductos.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appventaproductos.R
import com.example.appventaproductos.data.model.Carriola
import com.example.appventaproductos.ui.theme.AppVentaProductosTheme

@Composable
fun CarriolaCard(Car: Carriola, onClick: (Carriola) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .height(250.dp)
                .width(150.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color(0xFF9CABC4)),
                contentAlignment = Alignment.Center
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Image(
                            painter = painterResource(com.example.appventaproductos.R.drawable.carriola),
                            contentDescription = "Logo",
                            modifier = Modifier
                                .height(35.dp)
                                .padding(4.dp)
                        )
                        Text(
                            text = "Categorias",
                            fontSize = 6.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF282009),
                            modifier = Modifier.padding(4.dp)
                        )
                    }

                    Row {
                        Image(
                            painter = painterResource(Car.imagen),
                            contentDescription = "Foto del producto",
                            modifier = Modifier
                                .height(70.dp)
                                .padding(4.dp)
                        )
                        Spacer(modifier = Modifier.width(2.dp))
                        Column(
                            modifier = Modifier.padding(5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = Car.TítuloProducto,
                                fontSize = 13.sp,
                                color = Color(0xFF293167),
                                textAlign = TextAlign.Center,
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier.padding(2.dp)
                            )
                            Text(
                                text = "Titulo del producto",
                                color = Color(0xFF387C9D),
                                fontSize = 10.sp,
                                textAlign = TextAlign.Center
                            )
                        }
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(4.dp)
                    ) {
                        Text(
                            text = Car.Precio,
                            color = Color(0xFF387C9D),
                            fontSize = 8.sp,
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center
                        )

                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Row {
                                    Column {
                                        Text(
                                            text = "Condicion",
                                            fontSize = 8.sp,
                                            textAlign = TextAlign.Center,
                                            fontWeight = FontWeight.Bold,
                                            color = Color(0xFF387C9D),
                                            modifier = Modifier.height(20.dp)
                                        )
                                        Text(
                                            text = Car.Condición,
                                            fontSize = 5.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.height(15.dp)
                                        )
                                        Text(
                                            text = "Características",
                                            fontSize = 8.sp,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF387C9D),
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.height(20.dp)
                                        )
                                        Text(
                                            text = Car.Características,
                                            fontSize = 5.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.height(15.dp)
                                        )
                                        Text(
                                            text = "Peso",
                                            fontSize = 8.sp,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF387C9D),
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.height(20.dp)
                                        )
                                        Text(
                                            text = Car.Peso,
                                            fontSize = 5.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.height(15.dp)
                                        )
                                    }
                                    Spacer(modifier = Modifier.width(10.dp))
                                    Column {
                                        Text(
                                            text = "Material",
                                            fontSize = 8.sp,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF387C9D),
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.height(20.dp)
                                        )
                                        Text(
                                            text = Car.Materiales,
                                            fontSize = 5.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.height(15.dp)
                                        )
                                        Text(
                                            text = "Rango de edad",
                                            fontSize = 8.sp,
                                            textAlign = TextAlign.Center,
                                            color = Color(0xFF387C9D),
                                            fontWeight = FontWeight.Bold,
                                            modifier = Modifier.height(20.dp)
                                        )
                                        Text(
                                            text = Car.Rangoedad,
                                            fontSize = 5.sp,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier.height(15.dp)
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewCarriolaCard() {
    val c = Carriola(
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
        Rangoedad = "0 Mese en adelante",
        metodoEnvio = "Envío terrestre gratuito a todo el país (3-5 días hábiles)"


    )
    AppVentaProductosTheme {
        CarriolaCard(Car = c, onClick =  {})
    }
}
