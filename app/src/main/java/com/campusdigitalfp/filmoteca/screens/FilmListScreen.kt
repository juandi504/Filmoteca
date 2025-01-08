package com.campusdigitalfp.filmoteca.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.CreateFilm
import com.campusdigitalfp.filmoteca.FilmDataSource
import com.campusdigitalfp.filmoteca.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmListScreen(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
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
                },
                //Menú desplegable en la esquina superior derecha con el icono de 3 puntitos
                actions = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            imageVector = Icons.Filled.MoreVert,
                            contentDescription = "Menú desplegable"
                        )
                    }
                    // DropdownMenu que se desplegará al pulsar en los 3 puntitos
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                    ) {
                        DropdownMenuItem(
                            onClick = {
                                CreateFilm().nuevaPelicula()
                                expanded = false
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Add,
                                    contentDescription = "Añadir película",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },
                            text = { Text("Añadir película") }
                        )
                        DropdownMenuItem(
                            onClick = {
                                navController.navigate("about")
                                expanded = false
                            },
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Filled.Info,
                                    contentDescription = "Acerca de",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            },
                            text = { Text("Acerca de") }
                        )
                    }
                }
            )
        }

    ) { innerPadding ->
        // Usamos una columna para organizar la lista y el botón acerca de
        //Menú desplegable en la esquina superior derecha

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
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { navController.navigate("film/${film.id}") },
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
        }
    }
}
