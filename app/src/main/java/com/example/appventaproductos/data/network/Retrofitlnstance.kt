package mx.edu.utez.veterinaria.data.network

import com.example.appventaproductos.network.ApiService   // <- importa la interfaz donde esté realmente
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    // IP del emulador o tu red local
    private const val BASE_URL = "http://192.168.107.116:5000/"

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val api: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}
