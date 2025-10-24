package com.example.appventaproductos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class MenuViewModel : ViewModel() {
    fun goToCarriola(navController: NavController) {
        navController.navigate("carriola")
    }
}