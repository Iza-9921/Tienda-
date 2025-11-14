package com.example.appventaproductos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appventaproductos.data.model.Accesorio
import com.example.appventaproductos.repository.ProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AccesorioViewModel(private val repository: ProductRepository = ProductRepository()) : ViewModel() {

    private val _accesorioList = MutableStateFlow<List<Accesorio>>(emptyList())
    val accesorioList: StateFlow<List<Accesorio>> = _accesorioList.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage.asStateFlow()

    init {
        loadAccesorio()
    }

    fun loadAccesorio() {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                _accesorioList.value = repository.getAccesorios()
            } catch (e: Exception) {
                _errorMessage.value = "Error al cargar accesorio: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    fun createAccesorio(accesorio: Accesorio) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null
            try {
                val nuevoAccesorio = repository.createAccesorio(accesorio)
                if (nuevoAccesorio != null) {
                    loadAccesorio()
                } else {
                    _errorMessage.value = "Error al crear accesorio"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error al crear accesorio: ${e.message}"
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
                AccesorioViewModel()
            }
        }
    }
}