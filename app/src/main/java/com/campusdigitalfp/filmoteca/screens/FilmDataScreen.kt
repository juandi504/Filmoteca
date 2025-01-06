package com.campusdigitalfp.filmoteca.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

import com.campusdigitalfp.filmoteca.R



private fun abrirPaginaWeb(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url) // Establece la URL que quieres abrir
    }
    context.startActivity(intent) // Inicia la actividad
}


// Fila con la imagen y la columna de datos (título, director, etc.)
@Composable
fun RowDetallesPeli() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Imagen del cartel de la película
        Image(
            painter = painterResource(id = R.drawable.snatch),
            contentDescription = stringResource(id = R.string.cartelA),
            modifier = Modifier
                .size(width = 180.dp, height = 290.dp)
                .padding(end = 4.dp)
                .fillMaxHeight()
        )

        // Columna con información de la película
        Column(
            modifier = Modifier
                .weight(1f) // Reparte espacio con la imagen
                .padding(start = 10.dp),
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.tituloA),
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.director),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "Guy Ritchie",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stringResource(id = R.string.estreno),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = "2000",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stringResource(id = R.string.genero),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stringResource(id = R.string.formato),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


// Botón para ver la película en IMDB
@Composable
fun BotonImdb() {
    val context = LocalContext.current
    Button(onClick = { abrirPaginaWeb("https://www.imdb.com/es-es/title/tt0208092/",context) },
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
    ) {
        Text(stringResource(id = R.string.ver_en_imdb))
    }
}


//Notas de la película
@Composable
fun NotasPelicula() {
    Text(
        text = stringResource(id = R.string.notas),
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth()
    )
}


// Fila con 2 botones: "Volver" y "Editar"
@Composable
fun BotonesAcciones(navController: NavHostController, nombrePeli: String){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Botón para volver
        Button(
            onClick = { navController.popBackStack("list", false) },
            modifier = Modifier
                .weight(1f)
        ) {
            Text(stringResource(id = R.string.volver))
        }
        // Botón para Editar
        Button(
            onClick = { navController.navigate("edit/$nombrePeli") },
            modifier = Modifier
                .weight(1f)
        ) {
            Text(stringResource(id = R.string.editar_pelicula))
        }
    }
}


//Muestra el resultado de la edición (editada / cancelada)
@Composable
fun ResultadoEditar(result: String) {
    Text(
        text = if (result == "RESULT_OK") {
            stringResource(id = R.string.editada)
        } else {
            stringResource(id = R.string.cancelado)
        },
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(40.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDataScreen(navController: NavHostController, nombrePeli: String) {
    val result = navController.currentBackStackEntry?.savedStateHandle?.get<String>("result")
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = { Text(stringResource(id = R.string.Datos_pelicula)) },
                navigationIcon = { IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Atrás"
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            // 1) Fila con la imagen y la columna de datos
            item {
                RowDetallesPeli()
            }

             // 2) Botón para ver en IMDB
            item {
                BotonImdb()
            }

             // 3) Notas de la película
            item {
                NotasPelicula()
            }

             // 4) Fila con 2 botones (Volver y Editar)
            item {
                BotonesAcciones(navController, nombrePeli)
            }

            // 5) Mostrar resultado de edición (si existe)
            item {
                result?.let { ResultadoEditar(it) }
            }
        }
    }
}


