package com.campusdigitalfp.filmoteca.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.CreateFilm
import com.campusdigitalfp.filmoteca.Film
import com.campusdigitalfp.filmoteca.FilmDataSource
import com.campusdigitalfp.filmoteca.R

// Esta función muestra cada elemento de la lista de películas
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VistaFilm (film: Film, onClick: () -> Unit, onLongClick: () -> Unit, isSelected: Boolean) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .combinedClickable(onClick = onClick, onLongClick = onLongClick)
                .background(if (isSelected) MaterialTheme.colorScheme.primary.copy(alpha = 0.2f) else MaterialTheme.colorScheme.background)
                .animateContentSize()
        ) {
            Image(
                painter = painterResource(if (isSelected) R.drawable.seleccionada else film.imageResId),
                contentDescription = film.title,
                modifier = Modifier
                    .size(width = 100.dp, height = 110.dp)

            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 2.dp)
                    .align(Alignment.CenterVertically),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = film.title ?: "Sin título",
                    style = if (isSelected) {
                        MaterialTheme.typography.headlineMedium.copy(color = Color.Red)
                    } else {
                        MaterialTheme.typography.headlineMedium
                    }
                )
                Text(
                    text = film.director ?: "Desconocido",
                    style = if (isSelected) {
                        MaterialTheme.typography.bodyMedium.copy(color = Color.Red)
                    } else {
                        MaterialTheme.typography.bodyMedium
                    }
                )
            }
        }
    }

// Maneja la lógica de la lista completa según sea onclick o onlongclick
@Composable
fun VistaListaFilm(films: List<Film>, innerPadding: PaddingValues, navController: NavHostController,
                   isActionMode: MutableState<Boolean>, selectedFilms: MutableList<Film>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
    ) {
        // key = { it.id } indica que cada elemento en la lista films será identificado de manera única por su id
        items(films, key = { it.id }) { film ->
            VistaFilm(
                film = film,
                onClick = {
                    if (isActionMode.value) {
                        if (selectedFilms.contains(film)) {
                            selectedFilms.remove(film)
                            if (selectedFilms.isEmpty()) {
                                isActionMode.value = false
                            }
                        } else {
                            selectedFilms.add(film)
                        }
                    } else {
                        navController.navigate("film/${film.id}"){
                            launchSingleTop = true // Evita que puedas abrir dos veces la misma pelicula pulsando muy rápido
                        }
                    }
                },
                onLongClick = {
                    selectedFilms.add(film)
                    isActionMode.value = true
                },
                isSelected = selectedFilms.contains(film)
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmListScreen(navController: NavHostController) {
    var expanded by remember { mutableStateOf(false) }
    val isActionMode = remember { mutableStateOf(false) } // Controla si está activo el modo selección
    val selectedFilms = remember { mutableStateListOf<Film>() } // Lista de películas seleccionadas

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
                    // Texto del título dependiendo de si se estan seleccionando o no películas
                    Text(if (isActionMode.value) "Seleccionando películas" else stringResource(id = R.string.lista))
                },
                // Dependiendo del estado de isActionMode, la barra superior será diferente.
                actions = {
                    // Sí isActionMode es true se muestra la barra para eliminar peliculas
                    if (isActionMode.value) {
                        var showDialog by remember { mutableStateOf(false) }
                        // cuadro de diálogo de confirmación al eliminar películas, para evitar eliminaciones accidentales.
                        if (showDialog) {
                            AlertDialog(
                                onDismissRequest = { showDialog = false },
                                confirmButton = {
                                    TextButton(onClick = {
                                        FilmDataSource.films.removeAll(selectedFilms)
                                        selectedFilms.clear()
                                        isActionMode.value = false
                                        showDialog = false
                                    }) {
                                        Text("Eliminar")
                                    }
                                },
                                dismissButton = {
                                    TextButton(onClick = { showDialog = false }) {
                                        Text("Cancelar")
                                    }
                                },
                                title = { Text("Confirmar eliminación") },
                                text = { Text("¿Estás seguro de que deseas eliminar las películas seleccionadas?") }
                            )
                        }
                        // el `onClick` del botón de eliminación activa el cuadro de diálogo
                        IconButton(onClick = { showDialog = true }) {
                            Icon(
                                imageVector = Icons.Filled.Delete,
                                contentDescription = "Eliminar seleccionados"
                            )
                        }

                        //Menú desplegable en la esquina superior derecha con el icono de 3 puntitos
                    }
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
        VistaListaFilm(
            FilmDataSource.films,
            innerPadding,
            navController,
            isActionMode,
            selectedFilms
        )
    }
}
