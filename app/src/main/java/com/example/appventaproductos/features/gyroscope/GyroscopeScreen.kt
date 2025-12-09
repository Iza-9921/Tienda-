package com.example.appventaproductos.features.gyroscope

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.appventaproductos.R

@Composable
fun GyroscopeScreen(
    imageResId: Int,
    gyroscopeViewModel: GyroscopeViewModel = viewModel()
) {
    val uiState by gyroscopeViewModel.uiState.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Mueve tu dispositivo para rotar el producto", fontSize = 20.sp, modifier = Modifier.padding(bottom = 48.dp))

        Image(
            painter = painterResource(id = if (imageResId != 0) imageResId else R.drawable.ic_launcher_foreground),
            contentDescription = "Producto 3D",
            modifier = Modifier
                .size(250.dp)
                .graphicsLayer {
                    rotationX = uiState.rotationX
                    rotationY = uiState.rotationY
                    rotationZ = uiState.rotationZ
                }
        )
    }
}
