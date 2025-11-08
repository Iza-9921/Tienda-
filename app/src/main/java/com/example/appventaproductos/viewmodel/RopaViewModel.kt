package com.example.appventaproductos.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appventaproductos.data.local.AppDatabase
import com.example.appventaproductos.data.model.Ropa
import com.example.appventaproductos.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class RopaViewModel(private val repository: ProductRepository) : ViewModel() {

    val ropa: StateFlow<List<Ropa>> = repository
        .getRopa()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    init {
        viewModelScope.launch {
            repository.seedRopaIfNeeded()
        }
    }

    fun getById(id: Int): Flow<Ropa?> = repository.getRopaById(id)

    fun addRopa(item: Ropa) {
        viewModelScope.launch {
            repository.insertRopa(item)
        }
    }

    fun updateRopa(item: Ropa) {
        viewModelScope.launch {
            repository.updateRopa(item)
        }
    }

    fun removeById(id: Int) {
        viewModelScope.launch {
            repository.deleteRopa(id)
        }
    }

    fun clickRopa(ropa: Ropa) {
        println("Has hecho click en: ${ropa.TítuloProducto}")
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
                RopaViewModel(repository)
            }
        }
    }
}
