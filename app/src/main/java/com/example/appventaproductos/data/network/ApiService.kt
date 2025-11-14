package com.example.appventaproductos.network

import com.example.appventaproductos.data.model.Ropa
import com.example.appventaproductos.data.model.Carriola
import com.example.appventaproductos.data.model.Accesorio
import com.example.appventaproductos.data.model.CreateAccesorioResponse
import com.example.appventaproductos.data.model.CreateCarriolaResponse
import com.example.appventaproductos.data.model.CreateRopaResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiService {

    @GET("ropa")
    suspend fun getRopa(): Response<List<Ropa>>

    @Multipart
    @POST("ropa")
    suspend fun createRopa(
        @Part("nombre") nombre: RequestBody,
        @Part("talla") talla: RequestBody,
        @Part("precio") precio: RequestBody,
        @Part imagen: MultipartBody.Part? = null
    ): Response<CreateRopaResponse>

    @GET("carriolas")
    suspend fun getCarriolas(): Response<List<Carriola>>

    @Multipart
    @POST("carriolas")
    suspend fun createCarriola(
        @Part("marca") marca: RequestBody,
        @Part("modelo") modelo: RequestBody,
        @Part("precio") precio: RequestBody,
        @Part imagen: MultipartBody.Part? = null
    ): Response<CreateCarriolaResponse>

    @GET("accesorios")
    suspend fun getAccesorios(): Response<List<Accesorio>>

    @POST("accesorios")
    suspend fun createAccesorio(@Body accesorio: Accesorio): Response<CreateAccesorioResponse>
}






