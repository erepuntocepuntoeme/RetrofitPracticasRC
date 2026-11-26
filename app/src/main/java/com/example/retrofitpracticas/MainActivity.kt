package com.example.retrofitpracticas

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.retrofitpracticas.ui.list.ListaPokemonScreen
import com.example.retrofitpracticas.ui.publicaciones.PublicacionesScreen
import com.example.retrofitpracticas.ui.theme.RetrofitPracticasTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RetrofitPracticasTheme {
                var pestanaSeleccionada by remember { mutableIntStateOf(0) }
                val pestanas = listOf("Pokémon (GET)", "Posts (POST/PUT/DELETE)")

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                    ) {
                        TabRow(selectedTabIndex = pestanaSeleccionada) {
                            pestanas.forEachIndexed { indice, titulo ->
                                Tab(
                                    selected = pestanaSeleccionada == indice,
                                    onClick = { pestanaSeleccionada = indice },
                                    text = { Text(titulo) }
                                )
                            }
                        }

                        when (pestanaSeleccionada) {
                            0 -> ListaPokemonScreen()
                            1 -> PublicacionesScreen()
                        }
                    }
                }
            }
        }
    }
}