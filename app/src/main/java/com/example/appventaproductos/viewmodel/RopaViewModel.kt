package com.example.appventaproductos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appventaproductos.data.model.Ropa
import com.example.appventaproductos.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.io.File

class RopaViewModel(private val repository: ProductRepository = ProductRepository()) : ViewModel() {

    private val _ropaList = MutableStateFlow<List<Ropa>>(emptyList())
    val ropaList: StateFlow<List<Ropa>> = _ropaList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadRopa()
    }

    fun loadRopa() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                _ropaList.value = repository.getRopa()
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar ropa: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createRopa(nombre: String, talla: String, precio: Double, imagenFile: File?) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val nuevaRopa = repository.createRopa(nombre, talla, precio, imagenFile)
                if (nuevaRopa != null) {
                    loadRopa()
                } else {
                    _errorMessage.value = "Error al crear ropa"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al crear ropa: ${e.message}"
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
                RopaViewModel()
            }
        }
    }
}