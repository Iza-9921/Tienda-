package com.example.appventaproductos.repository

import com.example.appventaproductos.data.model.Ropa
import com.example.appventaproductos.data.model.Carriola
import com.example.appventaproductos.data.model.Accesorio
import com.example.appventaproductos.network.ApiService
import mx.edu.utez.veterinaria.data.network.RetrofitInstance
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

class ProductRepository(
    private val apiService: ApiService = RetrofitInstance.api
) {

    // Ajusta esto si tu base URL cambia; se usa para construir URLs absolutas de imágenes
    private val baseUrlForImages = "http://192.168.100.9:5000"

    // Helper: si la imagen viene como "/uploads/archivo.jpg" o "uploads/archivo.jpg"
    // la convierte a "http://<base>/uploads/archivo.jpg". Si ya es URL (http...), la deja.
    private fun makeFullImageUrl(possiblePath: String?): String? {
        if (possiblePath.isNullOrBlank()) return null
        return if (possiblePath.startsWith("http")) {
            possiblePath
        } else {
            if (possiblePath.startsWith("/")) "$baseUrlForImages$possiblePath"
            else "$baseUrlForImages/$possiblePath"
        }
    }

    suspend fun getRopa(): List<Ropa> {
        return try {
            val response = apiService.getRopa()
            if (response.isSuccessful) {
                val body = response.body() ?: emptyList()
                // si tu Ropa tiene un campo 'imagen' (String?) y quieres también una imagenUrl, adapta modelo
                body.map { ropa ->
                    // Si tu modelo tiene `imagen` como String y `imagenUrl` opcional, cámbialo aquí.
                    // Si no, esto no rompe nada — solo devolvemos la lista tal cual.
                    ropa.apply {
                        // reflection-free approach: si tienes campo imagen (String?), podrías
                        // setear otro campo imagenUrl si existe. Ajusta según tu modelo.
                    }
                }
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun createRopa(
        nombre: String,
        talla: String,
        precio: Double,
        imagenFile: File?
    ): Ropa? {
        return try {
            val nombreBody: RequestBody = nombre.toRequestBody("text/plain".toMediaTypeOrNull())
            val tallaBody: RequestBody = talla.toRequestBody("text/plain".toMediaTypeOrNull())
            val precioBody: RequestBody = precio.toString().toRequestBody("text/plain".toMediaTypeOrNull())

            val imagenPart: MultipartBody.Part? = imagenFile?.takeIf { it.exists() }?.let { file ->
                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("imagen", file.name, requestFile)
            }

            val response = apiService.createRopa(nombreBody, tallaBody, precioBody, imagenPart)
            if (response.isSuccessful) {
                // Ajusta esto según la forma de tu CreateRopaResponse
                response.body()?.ropa
            } else {
                // imprime el código para debug
                println("createRopa fallo: ${response.code()} ${response.message()}")
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
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
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun createCarriola(
        marca: String,
        modelo: String,
        precio: Double,
        imagenFile: File?
    ): Carriola? {
        return try {
            val marcaBody: RequestBody = marca.toRequestBody("text/plain".toMediaTypeOrNull())
            val modeloBody: RequestBody = modelo.toRequestBody("text/plain".toMediaTypeOrNull())
            val precioBody: RequestBody = precio.toString().toRequestBody("text/plain".toMediaTypeOrNull())

            val imagenPart: MultipartBody.Part? = imagenFile?.takeIf { it.exists() }?.let { file ->
                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                MultipartBody.Part.createFormData("imagen", file.name, requestFile)
            }

            val response = apiService.createCarriola(marcaBody, modeloBody, precioBody, imagenPart)
            if (response.isSuccessful) {
                response.body()?.carriola
            } else {
                println("createCarriola fallo: ${response.code()} ${response.message()}")
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
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
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun createAccesorio(accesorio: Accesorio): Accesorio? {
        return try {
            val response = apiService.createAccesorio(accesorio)
            if (response.isSuccessful) {
                response.body()?.accesorio
            } else {
                println("createAccesorio fallo: ${response.code()} ${response.message()}")
                null
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }
}
