package com.example.retrofitpracticas.data

import com.example.retrofitpracticas.data.remote.PokeApiService
import com.example.retrofitpracticas.data.remote.RetrofitClient
import com.example.retrofitpracticas.data.remote.aResumen
import com.example.retrofitpracticas.model.PokemonResumen
import com.example.retrofitpracticas.util.Resultado
import retrofit2.HttpException
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

class PokemonRepository(
    private val api: PokeApiService = RetrofitClient.api
) {
    suspend fun obtenerPokemones(limite: Int, desplazamiento: Int): Resultado<List<PokemonResumen>> =
        try {
            val respuesta = api.obtenerPokemones(limite, desplazamiento)
            Resultado.Exito(respuesta.results.map { it.aResumen() })
        } catch (e: CancellationException) {
            throw e // cancelar la corrutina no es un error
        } catch (e: HttpException) {
            Resultado.Error("El servidor respondió con un error (código ${e.code()}).")
        } catch (e: IOException) {
            Resultado.Error("No hay conexión a internet. Revisa tu red e inténtalo de nuevo.")
        } catch (e: Exception) {
            Resultado.Error("Ocurrió un error inesperado.")
        }
}