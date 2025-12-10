package com.example.appventaproductos.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import io.github.sceneview.Scene
import io.github.sceneview.model.ModelInstance
import io.github.sceneview.node.ModelNode
import io.github.sceneview.rememberEngine
import io.github.sceneview.rememberModelLoader

@Composable
fun Model3DViewScreen(modelPath: String) {
    var modelInstance by remember { mutableStateOf<ModelInstance?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    var error by remember { mutableStateOf<String?>(null) }

    val engine = rememberEngine()
    val modelLoader = rememberModelLoader(engine)

    LaunchedEffect(modelPath) {
        isLoading = true
        error = null
        try {
            val fullPath = "file:///android_asset/$modelPath"
            modelInstance = modelLoader.loadModelInstance(fullPath)
        } catch (e: Exception) {
            e.printStackTrace()
            error = "Fallo al cargar el modelo: ${e.message}"
        }
        isLoading = false
    }

    Box(modifier = Modifier.fillMaxSize()) {
        if (isLoading) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        } else if (modelInstance != null) {
            Scene(
                modifier = Modifier.fillMaxSize(),
                engine = engine,
            ) {
                modelInstance?.let { ModelNode(modelInstance = it) }
            }
        } else {
            Text(
                text = error ?: "Error: No se pudo cargar el modelo 3D.",
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
