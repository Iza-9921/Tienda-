package com.example.appventaproductos.viewmodel

import android.app.Application
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// Estado para la UI del giroscopio
data class GyroscopeUiState(
    val rotationX: Float = 0f,
    val rotationY: Float = 0f,
    val rotationZ: Float = 0f
)

class GyroscopeViewModel(application: Application) : AndroidViewModel(application), SensorEventListener {

    private val sensorManager = application.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private val gyroscopeSensor: Sensor? = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

    // Un factor para suavizar el movimiento y hacerlo menos brusco
    private val NS2S = 1.0f / 1000000000.0f
    private var timestamp = 0L

    private val _uiState = MutableStateFlow(GyroscopeUiState())
    val uiState = _uiState.asStateFlow()

    init {
        // Registra el listener cuando el ViewModel se inicia
        gyroscopeSensor?.let {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_UI)
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {
        if (event?.sensor?.type == Sensor.TYPE_GYROSCOPE) {
            if (timestamp != 0L) {
                // Calcula el tiempo transcurrido en segundos
                val dt = (event.timestamp - timestamp) * NS2S

                // Obtiene las velocidades de rotación en rad/s
                val axisX = event.values[0]
                val axisY = event.values[1]
                val axisZ = event.values[2]

                // Calcula el cambio en el ángulo y lo convierte a grados
                val deltaRotationX = axisX * dt * 180 / Math.PI.toFloat()
                val deltaRotationY = axisY * dt * 180 / Math.PI.toFloat()
                val deltaRotationZ = axisZ * dt * 180 / Math.PI.toFloat()

                // Acumula la rotación
                viewModelScope.launch {
                    _uiState.value = _uiState.value.copy(
                        rotationX = _uiState.value.rotationX + deltaRotationX,
                        rotationY = _uiState.value.rotationY + deltaRotationY,
                        rotationZ = _uiState.value.rotationZ + deltaRotationZ
                    )
                }
            }
            timestamp = event.timestamp
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        // No es necesario para esta implementación
    }

    // Se asegura de cancelar el registro del listener para ahorrar batería
    override fun onCleared() {
        super.onCleared()
        sensorManager.unregisterListener(this)
    }
}
