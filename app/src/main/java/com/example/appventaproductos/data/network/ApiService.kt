package com.example.appventaproductos.data.network

import retrofit2.Response
import retrofit2.http.*

// Modelo de Producto
data class Producto(
    val id: String? = null,
    val nombre: String,
    val descripcion: String? = null,
    val precio: Double,
    val categoria: String,
    val stock: Int,
    val imagen_url: String? = null
)

// Request para crear/actualizar producto
data class ProductoRequest(
    val nombre: String,
    val descripcion: String? = null,
    val precio: Double,
    val categoria: String,
    val stock: Int,
    val imagen_url: String? = null
)

// Respuesta genérica de la API usando tipo genérico
data class ApiResponse<T>(
    val success: Boolean,
    val message: String,
    val data: T? = null
)

// Interfaz de la API
interface ApiService {

    // Operaciones CRUD generales
    @GET("productos")
    suspend fun getAllProductos(): Response<List<Producto>>

    @GET("productos/{id}")
    suspend fun getProductoById(@Path("id") id: String): Response<Producto>

    @POST("productos")
    suspend fun createProducto(@Body producto: ProductoRequest): Response<ApiResponse<Producto>>

    @PUT("productos/{id}")
    suspend fun updateProducto(
        @Path("id") id: String,
        @Body producto: ProductoRequest
    ): Response<ApiResponse<Producto>>

    @DELETE("productos/{id}")
    suspend fun deleteProducto(@Path("id") id: String): Response<ApiResponse<Unit>>

    // Operaciones por categoría
    @GET("productos/categoria/{categoria}")
    suspend fun getProductosByCategoria(@Path("categoria") categoria: String): Response<List<Producto>>

    // Endpoints específicos
    @GET("accesorios")
    suspend fun getAccesorios(): Response<List<Producto>>

    @GET("carriolas")
    suspend fun getCarriolas(): Response<List<Producto>>

    @GET("ropa")
    suspend fun getRopa(): Response<List<Producto>>
}
