package com.example.retrofitpracticas.data.remote

import retrofit2.http.GET
import retrofit2.http.Query

interface PokeApiService {
    /** Lista paginada: /pokemon?limit=20&offset=0 */
    @GET("pokemon")
    suspend fun obtenerPokemones(
        @Query("limit") limite: Int,
        @Query("offset") desplazamiento: Int
    ): PokemonListResponse
}