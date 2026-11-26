package com.example.retrofitpracticas.data.remote

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface JsonPlaceholderService {
    @POST("posts")
    suspend fun crearPublicacion(
        @Body nueva: NuevaPublicacionDto
    ): PublicacionDto

    @PUT("posts/{id}")
    suspend fun actualizarPublicacion(
        @Path("id") id: Int,
        @Body actualizada: PublicacionDto
    ): PublicacionDto

    @DELETE("posts/{id}")
    suspend fun eliminarPublicacion(
        @Path("id") id: Int
    ): Response<Unit>
}