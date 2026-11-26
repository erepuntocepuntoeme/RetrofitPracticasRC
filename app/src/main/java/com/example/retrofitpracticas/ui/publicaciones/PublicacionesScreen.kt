package com.example.retrofitpracticas.ui.publicaciones

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun PublicacionesScreen(
    modifier: Modifier = Modifier,
    viewModel: PublicacionesViewModel = viewModel()
) {
    val estado by viewModel.uiState.collectAsState()
    var titulo by remember { mutableStateOf("Mi publicación Retrofit") }
    var cuerpo by remember { mutableStateOf("Contenido enviado desde Android") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text("Pruebas POST, PUT y DELETE", style = MaterialTheme.typography.titleLarge)

        OutlinedTextField(
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Título") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = cuerpo,
            onValueChange = { cuerpo = it },
            label = { Text("Cuerpo") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(
                onClick = { viewModel.probarPost(titulo, cuerpo) },
                modifier = Modifier.weight(1f)
            ) { Text("POST") }

            Button(
                onClick = { viewModel.probarPut(1, titulo, cuerpo) },
                modifier = Modifier.weight(1f)
            ) { Text("PUT #1") }

            Button(
                onClick = { viewModel.probarDelete(1) },
                modifier = Modifier.weight(1f)
            ) { Text("DELETE #1") }
        }

        if (estado.cargando) {
            CircularProgressIndicator()
        }

        estado.error?.let {
            Text(text = it, color = MaterialTheme.colorScheme.error)
        }

        Card(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = estado.mensajeResultado,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}