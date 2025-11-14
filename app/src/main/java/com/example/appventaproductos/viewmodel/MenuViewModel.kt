package com.example.appventaproductos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class MenuViewModel : ViewModel() {
    fun goToCarriola(navController: NavController) {
        navController.navigate("carriola")
    }
    fun goToRopa(navController: NavController) {
        navController.navigate("ropa")
    }
    fun goToAccesorio(navController: NavController) {
        navController.navigate("accesorio")
    }
}
