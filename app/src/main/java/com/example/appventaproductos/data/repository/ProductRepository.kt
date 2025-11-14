package com.example.appventaproductos.repository

import com.example.appventaproductos.data.model.Ropa
import com.example.appventaproductos.data.model.Carriola
import com.example.appventaproductos.data.model.Accesorio
import com.example.appventaproductos.network.ApiService
import com.example.appventaproductos.network.RetrofitInstance
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class ProductRepository {
    private val apiService: ApiService = RetrofitInstance.apiService

    suspend fun getRopa(): List<Ropa> {
        return try {
            val response = apiService.getRopa()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun createRopa(nombre: String, talla: String, precio: Double, imagenFile: File?): Ropa? {
        return try {
            val nombreBody = nombre.toRequestBody(MultipartBody.FORM)
            val tallaBody = talla.toRequestBody(MultipartBody.FORM)
            val precioBody = precio.toString().toRequestBody(MultipartBody.FORM)

            val imagenPart = imagenFile?.let { file ->
                MultipartBody.Part.createFormData(
                    "imagen",
                    file.name,
                    file.asRequestBody()
                )
            }

            val response = apiService.createRopa(nombreBody, tallaBody, precioBody, imagenPart)
            if (response.isSuccessful) {
                response.body()?.ropa
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }

    suspend fun getCarriolas(): List<Carriola> {
        return try {
            val response = apiService.getCarriolas()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun createCarriola(marca: String, modelo: String, precio: Double, imagenFile: File?): Carriola? {
        return try {
            val marcaBody = marca.toRequestBody(MultipartBody.FORM)
            val modeloBody = modelo.toRequestBody(MultipartBody.FORM)
            val precioBody = precio.toString().toRequestBody(MultipartBody.FORM)

            val imagenPart = imagenFile?.let { file ->
                MultipartBody.Part.createFormData(
                    "imagen",
                    file.name,
                    file.asRequestBody()
                )
            }

            val response = apiService.createCarriola(marcaBody, modeloBody, precioBody, imagenPart)
            if (response.isSuccessful) {
                response.body()?.carriola
            } else {
                print("aqui es el if")
                null
            }
        } catch (e: Exception) {
            print("aca es el catch")
            print(e)
            null
        }
    }

    suspend fun getAccesorios(): List<Accesorio> {
        return try {
            val response = apiService.getAccesorios()
            if (response.isSuccessful) {
                response.body() ?: emptyList()
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    suspend fun createAccesorio(accesorio: Accesorio): Accesorio? {
        return try {
            val response = apiService.createAccesorio(accesorio)
            if (response.isSuccessful) {
                response.body()?.accesorio
            } else {
                null
            }
        } catch (e: Exception) {
            null
        }
    }
}