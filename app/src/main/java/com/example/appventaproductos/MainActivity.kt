// MainActivity.kt
package com.example.appventaproductos

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.appventaproductos.ui.Navigation
import com.example.appventaproductos.ui.theme.AppVentaProductosTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppVentaProductosTheme {
                Navigation()
            }
        }
    }
}
