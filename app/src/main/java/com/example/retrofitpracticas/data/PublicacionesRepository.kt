package com.example.retrofitpracticas.data

import com.example.retrofitpracticas.data.remote.JsonPlaceholderClient
import com.example.retrofitpracticas.data.remote.JsonPlaceholderService
import com.example.retrofitpracticas.data.remote.NuevaPublicacionDto
import com.example.retrofitpracticas.data.remote.PublicacionDto
import com.example.retrofitpracticas.util.Resultado
import retrofit2.HttpException
import java.io.IOException
import kotlin.coroutines.cancellation.CancellationException

class PublicacionesRepository(
    private val api: JsonPlaceholderService = JsonPlaceholderClient.api
) {
    suspend fun crear(titulo: String, cuerpo: String, usuarioId: Int = 1): Resultado<PublicacionDto> =
        ejecutar {
            api.crearPublicacion(NuevaPublicacionDto(title = titulo, body = cuerpo, userId = usuarioId))
        }

    suspend fun actualizar(publicacion: PublicacionDto): Resultado<PublicacionDto> =
        ejecutar {
            api.actualizarPublicacion(id = publicacion.id, actualizada = publicacion)
        }

    suspend fun eliminar(id: Int): Resultado<Unit> =
        ejecutar {
            val respuesta = api.eliminarPublicacion(id)
            if (!respuesta.isSuccessful) throw HttpException(respuesta)
        }

    private suspend fun <T> ejecutar(bloque: suspend () -> T): Resultado<T> =
        try {
            Resultado.Exito(bloque())
        } catch (e: CancellationException) {
            throw e
        } catch (e: HttpException) {
            Resultado.Error("El servidor respondió con un error (código ${e.code()}).")
        } catch (e: IOException) {
            Resultado.Error("No hay conexión a internet. Revisa tu red e inténtalo de nuevo.")
        } catch (e: Exception) {
            Resultado.Error("Ocurrió un error inesperado.")
        }
}