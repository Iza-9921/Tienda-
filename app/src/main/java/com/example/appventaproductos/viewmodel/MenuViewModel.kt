package com.example.appventaproductos.viewmodel


import androidx.lifecycle.ViewModel
import androidx.navigation.NavController

class MenuViewModel: ViewModel(){

    fun goToCarriolas(navController: NavController){
        navController.navigate("verCarriolas")
    }

    fun goToRopa(navController: NavController){
        navController.navigate("verRopa")
    }

    fun goToBiberones(navController: NavController){
        navController.navigate("verBiberones")
    }

    fun goToCuna(navController: NavController){
        navController.navigate("verCunas")
    }

    fun goToLeches(navController: NavController){
        navController.navigate("verLeches")
    }
}