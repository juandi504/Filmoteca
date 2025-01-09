package com.campusdigitalfp.filmoteca.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.Film
import com.campusdigitalfp.filmoteca.FilmDataSource

@Composable
fun ButtonGuardar(navController: NavHostController, id: Int, filmModificada: Film) {
    // boton guardar
    Button(
        onClick = {
            // Se modifican los valores almacenados en FilmDataResource para la pelicula con ese id
            // con los valores de filmModificada
            FilmDataSource.films[id] = filmModificada
            // Log informativo cuando se pulsa guardar en la pantalla de edición FilmEditScreen
            Log.i("FilmEditScreen", "Se han guardado los cambios en la película con ID: $id")
            navController.previousBackStackEntry?.savedStateHandle?.set("result", "RESULT_OK")
            // Log de depuración donde indico el valor seteado en result que se devuelve a la screen anterior
            Log.d("FilmEditScreen", "result = RESULT_OK en savedStateHandle")
            navController.popBackStack()
            // Log informativo que indica que mediante navController.popBackStack() navegamos a la screen anterior
            Log.i("FilmEditScreen", "Navegando a la pantalla FilmDataScreen")
        },
    ) {
        Text(stringResource(R.string.Guardar))
    }
}

@Composable
fun ButtonCancelar(navController: NavHostController) {
    // boton cancelar
    Button(
        onClick = {
            // Log informativo cuando se pulsa cancelar en la pantalla de edición FilmEditScreen
            Log.i("FilmEditScreen", "Se han descartado los cambios.")
            navController.previousBackStackEntry?.savedStateHandle?.set("result", "RESULT_CANCELED")
            // Log de depuración donde indico el valor seteado en result que se devuelve a la screen anterior
            Log.d("FilmEditScreen", "result = RESULT_CANCELED en savedStateHandle")
            navController.popBackStack()
            // Log informativo que indica que mediante navController.popBackStack() navegamos a la screen anterior
            Log.i("FilmEditScreen", "Navegando a la pantalla FilmDataScreen")
        },
    ) {
        Text(stringResource(R.string.Cancelar))
    }
}

@Composable
fun BotonesSuperiores(navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        ButtonCaptura(navController, Modifier.weight(1f))
        SeleccionImagen(navController, Modifier.weight(1f))
    }
}

// Ajustamos las funciones para recibir `Modifier` adicional
@Composable
fun ButtonCaptura(navController: NavHostController, modifier: Modifier = Modifier) {
    Button(
        onClick = { /*sin implementar*/ },
        modifier = modifier
    ) {
        Text(
            stringResource(R.string.captura_foto),
            textAlign = TextAlign.Center, // Centra el texto
            modifier = Modifier.fillMaxWidth()
        ) // Texto ocupa todo el ancho del boton
    }
}

@Composable
fun SeleccionImagen(navController: NavHostController, modifier: Modifier = Modifier) {
    Button(
        onClick = { /*sin implementar*/ },
        modifier = modifier
    ) {
        Text(
            stringResource(R.string.seleccionar_foto),
            textAlign = TextAlign.Center, // Centra el texto
            modifier = Modifier.fillMaxWidth()
        ) // Texto ocupa todo el ancho del boton
    }
}

// Columna con los textfield
@Composable
fun ColumnaTextos(film: Film) {
    var title by remember { mutableStateOf(film.title) }
    var director by remember { mutableStateOf(film.director) }
    var anio by remember { mutableStateOf(film.year.toString()) }
    var linkImdb by remember { mutableStateOf(film.imdbUrl) }
    Column {
        // campo para título
        TextField(
            value = title ?: "",
            //
            onValueChange = {
                title = it // Actualiza el estado local del TextField
                film.title = it // Actualiza objeto Film su propiedad tittle
            },
            label = { Text(stringResource(R.string.titulo)) },
            placeholder = { Text(stringResource(R.string.titulo_enfocado)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        // campo para director
        TextField(
            value = director ?: "",
            onValueChange = {
                director = it // Actualiza el estado local del TextField
                film.director = it // Actualiza objeto Film su propiedad director
            },
            label = { Text(stringResource(R.string.director_texto)) },
            placeholder = { Text(stringResource(R.string.director_texto_enfocado)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        // campo para año
        TextField(
            value = anio,
            onValueChange = {
                anio = it // Actualiza el estado local del TextField
                film.year = it.toIntOrNull() ?: 0 // Actualiza objeto Film su propiedad year
            },
            label = { Text(stringResource(R.string.anio)) },
            placeholder = { Text(stringResource(R.string.anio_enfocado)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            )
        )
        // campo para enlace IMDB
        TextField(
            value = linkImdb ?: "",
            onValueChange = {
                linkImdb = it // Actualiza el estado local del TextField
                film.imdbUrl = it // Actualiza objeto Film su propiedad imdbUrl
            },
            label = { Text(stringResource(R.string.enlace_imdb)) },
            placeholder = { Text(stringResource(R.string.enlace_imdb)) },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )
    }
}

// DropdownMenu para genero
@Composable
fun GeneroDrop(film: Film) {
    // Controla si el menú desplegable está expandido o no.
    var expandedG by remember { mutableStateOf(false) }
    // Lista de opciones (géneros) obtenidas de los recursos mediante un map de getGenreString
    // si aumenta o disminuye la lista en Film.kt debe cambiarse aquí los valores actuales (0..4)
    val generos = (0..4).map { Film.getGenreString(it) }

    // Estado que guarda el género seleccionado actualmente. Por defecto, selecciona el de la pelicula actual.
    var selectedGenero by remember { mutableStateOf(Film.getGenreString(film.genre)) }
    // Columna que contiene el texto seleccionado y el DropdownMenu.
    Column {
        // Muestra el género seleccionado en un contenedor clickable que despliega el menú.
        Text(
            text = selectedGenero,// Genero actual de la pelicula
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                .padding(16.dp)
                .clickable(onClick = {
                    expandedG = !expandedG
                }) // Alterna el estado de expansión del menú al hacer click
        )

        // DropdownMenu que se despliega cuando `expanded` es `true`.
        DropdownMenu(
            expanded = expandedG,
            onDismissRequest = { expandedG = false } // Se cierra al hacer clic fuera de él.
        ) {
            // Itera sobre la lista de géneros y crea un elemento del menú para cada uno.
            generos.forEach { genero ->
                DropdownMenuItem(
                    // Muestra el nombre del género.
                    text = { Text(genero) },
                    onClick = {
                        selectedGenero = genero // Actualiza el género seleccionado.
                        film.genre =
                            generos.indexOf(genero) // Actualiza objeto Film su propiedad genre
                        expandedG = false // Cierra el menú.
                    }
                )
            }
        }
    }
}

// DropdownMenu para genero
@Composable
fun FormatoDrop(film: Film) {
    // Controla si el menú desplegable está expandido o no.
    var expandedf by remember { mutableStateOf(false) }

    // Lista de opciones (formatos) obtenidas de los recursos mediante un map de getFormatString
    // si aumenta o disminuye la lista en Film.kt debe cambiarse aquí los valores actuales (0..2)
    val formatos = (0..2).map { Film.getformatString(it) }
    // Estado que guarda el formato seleccionado actualmente. Por defecto, selecciona el de la pelicula actual.
    var selectedFormato by remember { mutableStateOf(Film.getformatString(film.format)) }
    // Columna que contiene el texto seleccionado y el DropdownMenu.
    Column {
        // Muestra el formato seleccionado en un contenedor clickable que despliega el menú.
        Text(
            text = selectedFormato, // Formato actual de la pelicula
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                .padding(16.dp)
                .clickable(onClick = {
                    expandedf = !expandedf
                }) // Alterna el estado de expansión del menú al hacer click
        )

        // DropdownMenu que se despliega cuando `expanded` es `true`.
        DropdownMenu(
            expanded = expandedf,
            onDismissRequest = { expandedf = false }
        ) {
            // Itera sobre la lista de formatos y crea un elemento del menú para cada uno.
            formatos.forEach { genero ->
                DropdownMenuItem(
                    // Muestra el nombre del formato
                    text = { Text(genero) },
                    onClick = {
                        selectedFormato = genero // Actualiza el formato seleccionado.
                        film.format =
                            formatos.indexOf(genero) // Actualiza objeto Film su propiedad format
                        expandedf = false // cierra el menu
                    }
                )
            }
        }
    }
}

// textfield de comentario
@Composable
fun Comentario(film: Film) {
    var comentario by remember { mutableStateOf(film.comments) }
    // campo para comentario
    TextField(
        value = comentario ?: "",
        onValueChange = {
            comentario = it // Actualiza el comentario
            film.comments = it // Actualiza objeto Film su propiedad comments
        },
        label = { Text(stringResource(R.string.comentario)) },
        placeholder = { Text(stringResource(R.string.comentario_enfocado)) },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            capitalization = KeyboardCapitalization.Sentences
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmEditScreen(navController: NavHostController, id: Int) {
    val film = FilmDataSource.films[id] // Selección de la película con id igual al parámetro
    // Copia temporal de la pelicula para implementar si debo guardar o descartar cambios
    var filmModificada by remember { mutableStateOf(film.copy()) }

    Scaffold(
        // Definición de la barra superior dentro del Scaffold
        topBar = {
            TopAppBar(
                // Definimos los colores personalizados para la TopAppBar
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer, // Color de fondo de la barra
                    titleContentColor = MaterialTheme.colorScheme.primary, // Color del título
                ),
                // Icono de "HOME" en la izquierda que nos lleva a FilmListScreen
                navigationIcon = {
                    Box(
                        modifier = Modifier
                            .clickable { navController.popBackStack("list", false) }
                            .padding(8.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Home,
                            contentDescription = "Volver a pantalla de inicio"
                        )
                    }
                },
                // Definimos el título que aparecerá en la TopAppBar
                title = {
                    Text(stringResource(id = R.string.editar_pelicula)) // Texto del título
                },
            )
        }) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            item {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // Imagen del cartel de la película
                    Image(
                        painter = painterResource(filmModificada.imageResId),
                        contentDescription = filmModificada.title,
                        modifier = Modifier
                            .size(width = 70.dp, height = 120.dp)
                            .padding(end = 4.dp)
                            .fillMaxHeight()
                    )
                    BotonesSuperiores(navController)

                }
                // Como parámetro paso la copia de la película y luego en el botón guardar es donde implementaré
                // como conservar los cambios
                ColumnaTextos(filmModificada)
                GeneroDrop(filmModificada)
                FormatoDrop(filmModificada)
                Comentario(filmModificada)
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    ButtonGuardar(navController, id, filmModificada)
                    ButtonCancelar(navController)
                }
            }
        }
    }
}

