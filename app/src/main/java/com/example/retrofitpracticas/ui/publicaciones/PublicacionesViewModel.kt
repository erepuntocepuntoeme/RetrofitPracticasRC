package com.example.retrofitpracticas.ui.publicaciones

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.retrofitpracticas.data.PublicacionesRepository
import com.example.retrofitpracticas.data.remote.PublicacionDto
import com.example.retrofitpracticas.util.Resultado
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PublicacionesUiState(
    val cargando: Boolean = false,
    val mensajeResultado: String = "Presiona un botón para probar POST, PUT o DELETE",
    val error: String? = null
)

class PublicacionesViewModel(
    private val repositorio: PublicacionesRepository = PublicacionesRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(PublicacionesUiState())
    val uiState: StateFlow<PublicacionesUiState> = _uiState.asStateFlow()

    fun probarPost(titulo: String, cuerpo: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true, error = null) }
            when (val res = repositorio.crear(titulo, cuerpo)) {
                is Resultado.Exito -> _uiState.update {
                    it.copy(
                        cargando = false,
                        mensajeResultado = "POST exitoso -> ID: ${res.datos.id}\nTítulo: ${res.datos.title}\nCuerpo: ${res.datos.body}"
                    )
                }
                is Resultado.Error -> _uiState.update {
                    it.copy(cargando = false, error = res.mensaje)
                }
            }
        }
    }

    fun probarPut(id: Int, nuevoTitulo: String, nuevoCuerpo: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true, error = null) }
            val dto = PublicacionDto(id = id, title = nuevoTitulo, body = nuevoCuerpo, userId = 1)
            when (val res = repositorio.actualizar(dto)) {
                is Resultado.Exito -> _uiState.update {
                    it.copy(
                        cargando = false,
                        mensajeResultado = "PUT exitoso (ID ${res.datos.id}) ->\nTítulo: ${res.datos.title}\nCuerpo: ${res.datos.body}"
                    )
                }
                is Resultado.Error -> _uiState.update {
                    it.copy(cargando = false, error = res.mensaje)
                }
            }
        }
    }

    fun probarDelete(id: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(cargando = true, error = null) }
            when (val res = repositorio.eliminar(id)) {
                is Resultado.Exito -> _uiState.update {
                    it.copy(
                        cargando = false,
                        mensajeResultado = "DELETE exitoso -> Publicación #$id eliminada (HTTP 200)"
                    )
                }
                is Resultado.Error -> _uiState.update {
                    it.copy(cargando = false, error = res.mensaje)
                }
            }
        }
    }
}