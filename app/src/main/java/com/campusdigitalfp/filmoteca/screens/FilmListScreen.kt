package com.campusdigitalfp.filmoteca.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.FilmDataSource
import com.campusdigitalfp.filmoteca.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmListScreen(navController: NavHostController) {
    Scaffold(
        // Definición de la barra superior dentro del Scaffold
        topBar = {
            TopAppBar(
                // Definimos los colores personalizados para la TopAppBar
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer, // Color de fondo de la barra
                    titleContentColor = MaterialTheme.colorScheme.primary, // Color del título
                ),
                // Definimos el título que aparecerá en la TopAppBar
                title = {
                    Text(stringResource(id = R.string.lista)) // Texto del título
                }
            )
        }
    ) { innerPadding ->
        // Usamos una columna para organizar la lista y el botón acerca de
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp)
        ) {
            // LazyColumn para ir mostrando los elementos de la pantalla en ese momento
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
            ) {
                // Iteramos cada elemento que tiene la lista mutable de FilmDataSource
                // y mediante la función lambda mostramos la imagen de portada y el título
                // y director (titulo-director) organizado en una columna
                items(FilmDataSource.films) { film ->
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Imagen del cartel de la película
                        Image(
                            painter = painterResource(film.imageResId),
                            contentDescription = film.title,
                            modifier = Modifier
                                .size(width = 70.dp, height = 140.dp)
                                .padding(end = 4.dp)
                                .fillMaxHeight()
                        )
                        Column(
                            modifier = Modifier
                                .weight(1f) // Reparte espacio con la imagen
                                .padding(start = 10.dp),
                            horizontalAlignment = Alignment.Start
                        ) {
                            Text(
                                text = film.toString(), // Usando el metodo toString() de film
                                style = MaterialTheme.typography.headlineMedium,
                                modifier = Modifier.padding(vertical = 4.dp),

                                )
                            Text(
                                text = film.director ?: "Desconocido",
                                style = MaterialTheme.typography.bodyMedium,
                            )
                        }
                    }
                }
            }
            // Fuera del LazyColumn se sitúa el botón acerca de
            Button(
                onClick = { navController.navigate("about") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            ) {
                Text(stringResource(id = R.string.Acerca_de))
            }
        }
    }
}
