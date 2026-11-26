package com.example.retrofitpracticas.data.remote

import com.example.retrofitpracticas.model.PokemonResumen

// Respuesta de GET https://pokeapi.co/api/v2/pokemon?limit=20&offset=0
data class PokemonListResponse(
    val count: Int,
    val next: String?,
    val results: List<PokemonItemDto>
)

data class PokemonItemDto(
    val name: String,
    val url: String
)

// Función de mapeo: convierte el DTO de la API al modelo limpio de la app
fun PokemonItemDto.aResumen(): PokemonResumen {
    val id = url.trimEnd('/').substringAfterLast('/').toInt()
    return PokemonResumen(
        id = id,
        nombre = name.replaceFirstChar { it.uppercase() },
        imagenUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/$id.png"
    )
}