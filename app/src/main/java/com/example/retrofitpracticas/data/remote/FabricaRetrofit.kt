package com.example.retrofitpracticas.data.remote

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object FabricaRetrofit {
    private val clienteHttp: OkHttpClient by lazy {
        OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS) // tiempo máximo para conectarse
            .readTimeout(15, TimeUnit.SECONDS)    // tiempo máximo esperando la respuesta
            .writeTimeout(15, TimeUnit.SECONDS)   // tiempo máximo enviando el cuerpo
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    /** `baseUrl` debe terminar en "/". */
    fun crear(baseUrl: String): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}