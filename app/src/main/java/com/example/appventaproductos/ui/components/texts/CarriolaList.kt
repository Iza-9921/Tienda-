package com.example.appventaproductos.ui.components.texts

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
fun CarriolaList(lista: List<Carriola>, x: (Carriola) -> Unit) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        //Iterar la lista en Java es con foreach
        //Pero en kotlin vamos a ocupar una funcion items
        items(items = lista, key = {it.id} ) {Carriola ->
            CarriolaCard(Carriola) { x(Carriola) }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLicList(){
    val lista = listOf(
        Carriola(
            1,
            R.drawable.carriola,
            "Carriola Modular Premium 3-en-1 (Moises, Asiento Reversible y Autoasiento)",
            "MXN 8,999.00",
            "Nueva(Certificada y Sellada)",
            "- Ruedas de goma todo terreno con suspensión en las 4 ruedas.\n" +
                    "    - Amplia canasta de almacenamiento inferior." +
                    "- Incluye: Portavasos y cubrepiés.",
            "11.5 kg (Carriola con asiento)",
            "Chasis de aluminio ligero de alta resistencia y textiles hipoalergénicos",
            "0 Mese en adelante",
            "Envío terrestre gratuito a todo el país (3-5 días hábiles)"


        ),
        Carriola(
            2,
            R.drawable.carriola,
            "Carriola Ultra Ligera de Viaje tipo Paraguas con Certificación IATA",
            "MXN 3,450.00",
            "Nueva (Certificada por fabricante)",
            "- Diseño ultra compacto, apto para cabina de avión (equipaje de mano).\n" +
                    "    - Peso pluma, fácil de transportar.\n" +
                    "    - Respaldo reclinable hasta 150 grados para siestas.\n" +
                    "    - Freno de pie de un toque.",
            "2.6 kg",
            "Marco de acero reforzado",
            "A partir de los 6 meses",
            "Envío Express (24-48 horas) con costo adicional de MXN 150.00"
        ),
        Carriola(
            3,
            R.drawable.carriola,
            "Carriola Doble Tándem con Asientos Reversibles para Hermanos",
            "MXN 12,800.00",
            "Nueva (Empaque original)",
            "- Dos asientos modulares que pueden colocarse en más de 10 configuraciones.\n" +
                    "    - Asientos totalmente reclinables e independientes.\n" +
                    "    - Compatible con autoasientos de la misma marca.",
            "18.5 kg",
            "Estructura de metal premium, telas resistentes al desgaste",
            "Permite 2 niños, desde recién nacidos (0 meses)",
            "Envío por paquetería consolidada, entrega a domicilio sin costo."
        ),
        Carriola(
            4,
            R.drawable.carriola,
            "Carriola Deportiva Jogger de 3 Ruedas con Freno de Mano y Amortiguación",
            "MXN 6,999.00",
            "Nueva (Ideal para padres activos)",
            "- Diseño aerodinámico de 3 ruedas grandes de aire para una estabilidad superior al correr.\n" +
                    "    - Freno de mano de tambor para desaceleración y frenado de emergencia.\n" +
                    "    - Rueda delantera giratoria con bloqueo para modo jogging o caminata rápida.\n" +
                    "    - Suspensión ajustable de alto rendimiento.",
            "13.8 kg",
            "Estructura de aluminio aeronáutico y neumáticos de caucho",
            "Recomendada a partir de los 6 meses (cuando el bebé se sostiene sentado)",
            "Recolección local gratuita en Ciudad de México o envío nacional con tarifa plana de MXN 200.00"
        ),

        )
    AppVentaProductosTheme {
        CarriolaList(lista) { }
    }
}