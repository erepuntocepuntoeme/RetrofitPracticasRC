package com.example.retrofitpracticas.data.remote

data class NuevaPublicacionDto(
    val title: String,
    val body: String,
    val userId: Int
)

data class PublicacionDto(
    val id: Int,
    val title: String,
    val body: String,
    val userId: Int
)