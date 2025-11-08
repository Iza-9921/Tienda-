package com.example.appventaproductos.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.appventaproductos.data.local.AppDatabase
import com.example.appventaproductos.data.model.Carriola
import com.example.appventaproductos.data.repository.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class CarriolaViewModel(private val repository: ProductRepository) : ViewModel() {

    val carriola: StateFlow<List<Carriola>> = repository
        .getCarriolas()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())

    init {
        viewModelScope.launch {
            repository.seedCarriolaIfNeeded()
        }
    }

    fun getById(id: Int): Flow<Carriola?> = repository.getCarriolaById(id)

    fun addCarriola(item: Carriola) {
        viewModelScope.launch {
            repository.insertCarriola(item)
        }
    }

    fun updateCarriola(item: Carriola) {
        viewModelScope.launch {
            repository.updateCarriola(item)
        }
    }

    fun removeById(id: Int) {
        viewModelScope.launch {
            repository.deleteCarriola(id)
        }
    }

    fun clickPerson(carriola: Carriola) {
        println("Has hecho click en: ${carriola.TítuloProducto}")
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
                CarriolaViewModel(repository)
            }
        }
    }
}
