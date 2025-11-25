package com.example.appventaproductos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appventaproductos.data.model.Carriola
import com.example.appventaproductos.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File

class CarriolaViewModel(private val repository: ProductRepository = ProductRepository()) : ViewModel() {

    private val _carriolaList = MutableStateFlow<List<Carriola>>(emptyList())
    val carriolaList: StateFlow<List<Carriola>> = _carriolaList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadCarriola()
    }

    fun loadCarriola() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                _carriolaList.value = repository.getCarriolas()
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar carriola: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createCarriola(marca: String, modelo: String, precio: Double, imagenFile: File?) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val nuevaCarriola = repository.createCarriola(marca, modelo, precio, imagenFile)
                if (nuevaCarriola != null) {
                    loadCarriola()
                } else {
                    _errorMessage.value = "Error al crear carriola"
                    error("Error al crear carriola")
                    println("Lo que sea")
                    println("Lo que sea")
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al crear carriola: ${e.message}"
                error("Error al crear carriola ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun clearError() {
        _errorMessage.value = null
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                CarriolaViewModel()
            }
        }
    }
}