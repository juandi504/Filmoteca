package com.campusdigitalfp.filmoteca.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import com.campusdigitalfp.filmoteca.Film
import com.campusdigitalfp.filmoteca.FilmDataSource

import com.campusdigitalfp.filmoteca.R
import java.util.UUID

private fun abrirPaginaWeb(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url) // Establece la URL que quieres abrir
    }
    try {
        context.startActivity(intent) // Inicia la actividad
    } catch (e: Exception) {
        // Maneja el caso cuando no haya una dirección web correcta en el enlace de IMDB
        Toast.makeText(context, "Dirección web inválida", Toast.LENGTH_SHORT)
            .show()
    }
}

// Fila con la imagen y la columna de datos (título, director, etc.)
@Composable
fun RowDetallesPeli(film: Film) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Imagen del cartel de la película
        Image(
            painter = painterResource(film.imageResId),
            contentDescription = film.title,
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
                text = film.title?:"Titulo desconocido",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = stringResource(id = R.string.director),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = film.director?:"Desconocido",
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = stringResource(id = R.string.estreno),
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = film.year.toString(),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = Film.getGenreString(film.genre),
                style = MaterialTheme.typography.bodyMedium
            )
            Text(
                text = Film.getformatString(film.format),
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


// Botón para ver la película en IMDB
@Composable
fun BotonImdb(film: Film) {
    val context = LocalContext.current
    Button(onClick = { abrirPaginaWeb(film.imdbUrl?:"",context) },
        modifier = Modifier
            .padding(vertical = 4.dp)
            .fillMaxWidth()
    ) {
        Text(stringResource(id = R.string.ver_en_imdb))
    }
}


//Notas de la película
@Composable
fun NotasPelicula(film: Film) {
    Text(
        text = film.comments?:"",
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Start,
        modifier = Modifier.fillMaxWidth()
    )
}


// Fila con 2 botones: "Volver" y "Editar"
@Composable
fun BotonesAcciones(navController: NavHostController, film: Film){
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
            onClick = {
                navController.navigate("edit/${film.id}") {
                    launchSingleTop = true // Evita que puedas abrir dos veces la misma pantalla pulsando muy rápido
                }
            },
            modifier = Modifier
                .weight(1f)
        ) {
            Text(stringResource(id = R.string.editar_pelicula))
        }
    }
}


// Modificada la muestra el resultado de la edición mediante un Toast
@Composable
fun ResultadoEditar(result: String) {
    val context = LocalContext.current
     if (result == "RESULT_OK") {
            Toast.makeText(context, "Cambios guardados", Toast.LENGTH_SHORT).show()
        } else if (result == "RESULT_CANCELED"){
            Toast.makeText(context, "Cambios cancelados", Toast.LENGTH_SHORT).show()
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDataScreen(navController: NavHostController, id: UUID) {
    val result = navController.currentBackStackEntry?.savedStateHandle?.get<String>("result")
    // Busca la película con el UUID proporcionado
    val film = FilmDataSource.films.find { it.id == id }

    // Validamos si film es null
    if (film == null) {
        navController.popBackStack() // Volvemos a la pantalla anterior si no existe
        return // Finalizamos la ejecución de la función
    }
    // Si film no es null todo se ejecuta con normalidad
    result?.let { ResultadoEditar(it) }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                // Icono de "HOME" en la izquierda que nos lleva a FilmListScreen
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .clickable { navController.popBackStack("list", false) }
                            .padding(8.dp)
                    ){
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Volver a pantalla de inicio"
                        )
                    }
                },
                title = { Text(stringResource(id = R.string.Datos_pelicula)) },
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
                RowDetallesPeli(film)
            }

             // 2) Botón para ver en IMDB
            item {
                BotonImdb(film)
            }

             // 3) Notas de la película
            item {
                NotasPelicula(film)
            }

             // 4) Fila con 2 botones (Volver y Editar)
            item {
                BotonesAcciones(navController, film)
            }
        }
    }
}


