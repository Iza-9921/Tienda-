package com.example.appventaproductos.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appventaproductos.data.local.AppDatabase
import com.example.appventaproductos.data.model.Accesorios
import com.example.appventaproductos.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AccesoriosViewModel(private val repository: ProductRepository) : ViewModel() {

    val accesorios: StateFlow<List<Accesorios>> = repository
        .getAccesorios()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    init {
        viewModelScope.launch {
            repository.seedAccesoriosIfNeeded()
        }
    }

    fun getById(id: Int): Flow<Accesorios?> = repository.getAccesorioById(id)

    fun addAccesorios(item: Accesorios) {
        viewModelScope.launch {
            repository.insertAccesorio(item)
        }
    }

    fun updateAccesorios(item: Accesorios) {
        viewModelScope.launch {
            repository.updateAccesorio(item)
        }
    }

    fun removeById(id: Int) {
        viewModelScope.launch {
            repository.deleteAccesorio(id)
        }
    }

    fun clickPerson(accesorios: Accesorios) {
        println("Has hecho click en: ${accesorios.TítuloProducto}")
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as Application)
                val database = AppDatabase.getInstance(application)
                val repository = ProductRepository(
                    database.ropaDao(),
                    database.carriolaDao(),
                    database.accesorioDao()
                )
                AccesoriosViewModel(repository)
            }
        }
    }
}
