package com.example.retrofitpracticas.data.remote

object JsonPlaceholderClient {
    private const val BASE_URL = "https://jsonplaceholder.typicode.com/"

    val api: JsonPlaceholderService by lazy {
        FabricaRetrofit.crear(BASE_URL).create(JsonPlaceholderService::class.java)
    }
}