package com.example.retrofitpracticas.ui.list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.retrofitpracticas.ui.components.MensajeDeError

@Composable
fun ListaPokemonScreen(
    modifier: Modifier = Modifier,
    viewModel: ListaPokemonViewModel = viewModel()
) {
    val estado by viewModel.uiState.collectAsState()
    val error = estado.error

    when {
        estado.cargando -> Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        error != null -> MensajeDeError(
            mensaje = error,
            onReintentar = viewModel::cargar,
            modifier = modifier
        )
        else -> LazyColumn(modifier = modifier.fillMaxSize()) {
            items(estado.pokemones, key = { it.id }) { pokemon ->
                Text(
                    text = "#${pokemon.id} ${pokemon.nombre}",
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}