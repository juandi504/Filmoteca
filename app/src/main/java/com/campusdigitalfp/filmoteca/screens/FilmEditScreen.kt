package com.campusdigitalfp.filmoteca.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.ui.theme.FilmotecaTheme
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.ui.graphics.Color


@Composable
fun ButtonGuardar(navController: NavHostController) {
    // boton guardar
    Button(
        onClick = {
            navController.previousBackStackEntry?.savedStateHandle?.set("result", "RESULT_OK")
            navController.popBackStack()
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
            navController.previousBackStackEntry?.savedStateHandle?.set("result", "RESULT_CANCELED")
            navController.popBackStack()
        },
    ) {
        Text(stringResource(R.string.Cancelar))
    }
}

@Composable
fun BotonesSuperiores(navController: NavHostController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(6.dp), // por ejemplo, deja 16dp entre los botones
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
        Text(stringResource(R.string.captura_foto),
            textAlign = TextAlign.Center, // Centra el texto
            modifier = Modifier.fillMaxWidth()) // Para que el texto llene todo el ancho del Button)
    }
}

@Composable
fun SeleccionImagen(navController: NavHostController, modifier: Modifier = Modifier) {
    Button(
        onClick = { /*sin implementar*/ },
        modifier = modifier
    ) {
        Text(stringResource(R.string.seleccionar_foto),
            textAlign = TextAlign.Center, // Centra el texto
            modifier = Modifier.fillMaxWidth())
    }
}

// Columna con los textfield
@Composable
fun ColumnaTextos() {
    var title by remember { mutableStateOf("") }
    var director by remember { mutableStateOf("") }
    var anio by remember { mutableStateOf("") }
    var linkImdb by remember { mutableStateOf("") }
    Column(){
        // campo para título
        TextField(
            value = title,
            onValueChange = { newText -> title = newText },
            label = { Text(stringResource(R.string.titulo))},
            placeholder = { Text(stringResource(R.string.titulo_enfocado))},
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                capitalization = KeyboardCapitalization.Sentences
            )
        )

        // campo para director
        TextField(
            value = director,
            onValueChange = { newText -> director = newText },
            label = { Text(stringResource(R.string.director_texto))},
            placeholder = { Text(stringResource(R.string.director_texto_enfocado))},
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
            onValueChange = { newText -> anio = newText },
            label = { Text(stringResource(R.string.anio))},
            placeholder = { Text(stringResource(R.string.anio_enfocado))},
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Number,
            )
        )
        // campo para enlace IMDB
        TextField(
            value = linkImdb ,
            onValueChange = { newText -> linkImdb  = newText },
            label = { Text(stringResource(R.string.enlace_imdb))},
            placeholder = { Text(stringResource(R.string.enlace_imdb))},
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
fun GeneroDrop() {
    // Contexto necesario para acceder a los recursos.
    val context = LocalContext.current
    // Controla si el menú desplegable está expandido o no.
    var expandedG by remember { mutableStateOf(false) }
    // Lista de opciones (géneros) obtenidas de los recursos.
    val generos = context.resources.getStringArray(R.array.genero_list).toList()
    // Estado que guarda el género seleccionado actualmente. Por defecto, selecciona el primero.
    var selectedGenero by remember { mutableStateOf(generos[0]) }
    // Columna que contiene el texto seleccionado y el DropdownMenu.
    Column {
        // Muestra el género seleccionado en un contenedor clickable que despliega el menú.
        Text(
            text = selectedGenero,// Género seleccionado actualmente.
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                .padding(16.dp)
                .clickable (onClick = { expandedG = !expandedG }) // Alterna el estado de expansión del menú al hacer click
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
                        expandedG = false // Cierra el menú.
                    }
                )
            }
        }
    }
}

// DropdownMenu para genero
@Composable
fun FormatoDrop() {
    // Contexto necesario para acceder a los recursos.
    val context = LocalContext.current
    // Controla si el menú desplegable está expandido o no.
    var expandedf by remember { mutableStateOf(false) }
    // Lista de opciones (formatos) obtenidas de los recursos.
    val formatos = context.resources.getStringArray(R.array.formato_list).toList()
    // Estado que guarda el formato seleccionado actualmente. Por defecto, selecciona el primero.
    var selectedFormato by remember { mutableStateOf(formatos[0]) }
    // Columna que contiene el texto seleccionado y el DropdownMenu.
    Column {
        // Muestra el formato seleccionado en un contenedor clickable que despliega el menú.
        Text(
            text = selectedFormato,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.5f))
                .padding(16.dp)
                .clickable (onClick = { expandedf = !expandedf }) // Alterna el estado de expansión del menú al hacer click
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
                        expandedf = false // cierra el menu
                    }
                )
            }
        }
    }
}

// textfield de comentario
@Composable
fun Comentario() {
    var comentario by remember { mutableStateOf("") }
    // campo para comentario
    TextField(
        value = comentario,
        onValueChange = { newText -> comentario = newText },
        label = { Text(stringResource(R.string.comentario))},
        placeholder = { Text(stringResource(R.string.comentario_enfocado))},
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
fun FilmEditScreen(navController: NavHostController, nombrePeli: String) {

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
                    Text(stringResource(id = R.string.editar_pelicula)) // Texto del título
                },
                // Ícono de navegación en la izquierda (botón de retroceso)
                navigationIcon = {
                    IconButton(onClick = {
                        navController.previousBackStackEntry?.savedStateHandle?.set("result", "RESULT_CANCELED")
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack, // Icono de retroceso
                            contentDescription = "Atrás" // Descripción del ícono (para accesibilidad)
                        )
                    }
                }
            )
        }) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                // Imagen del cartel de la película
                Image(
                    painter = painterResource(id = R.drawable.snatch),
                    contentDescription = stringResource(id = R.string.cartelA),
                    modifier = Modifier
                        .size(width = 70.dp, height = 120.dp)
                        .padding(end = 4.dp)
                        .fillMaxHeight()
                )
                BotonesSuperiores(navController)

            }

            ColumnaTextos();
            GeneroDrop()
            FormatoDrop()
            Comentario()
            Spacer(modifier = Modifier.height(16.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                ButtonGuardar(navController)
                ButtonCancelar(navController)
            }




        }
    }
}

