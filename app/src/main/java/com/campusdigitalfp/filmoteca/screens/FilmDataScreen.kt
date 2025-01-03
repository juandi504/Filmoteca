package com.campusdigitalfp.filmoteca.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R
import com.campusdigitalfp.filmoteca.ui.theme.FilmotecaTheme


@Composable
fun ButtonPelRel(navController: NavHostController, nombrePeli: String) {
    // esta variable almacena el nombre de la pelicula dependiendo de cual este mostrando su FilmDataScreen
    // Si es pelicula A, muestra pelicula B al pulsar en pelicula relacionada y viceversa
    val peliculaRelacionada = if (nombrePeli == "Película A") "Película B" else "Película A"

    // El boton hace uso de la variable para navegar con ese parámetro
    Button(
        onClick = { navController.navigate("data/$peliculaRelacionada") },
    ) {
        Text(stringResource(id = R.string.ver_pelicula_rel))
    }
}

@Composable
fun ButtonEditar(navController: NavHostController, nombrePeli: String) {
    // añado el parámetro nombrePeli para poderlo mostrar en la pantalla editar
    Button(
        onClick = { navController.navigate("edit/$nombrePeli") },
    ) {
        Text(stringResource(id = R.string.editar_pelicula))
    }
}

@Composable
fun ButtonReturn(navController: NavHostController) {

    Button(
        onClick = { navController.popBackStack("list", false) },
    ) {
        Text(stringResource(id = R.string.volver_principal))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmDataScreen(navController: NavHostController, nombrePeli: String) {
    // variable para almacenar el resultado de la pantalla de editar cuando se pulsa
    // guardar o cancelar
    val result = navController.currentBackStackEntry?.savedStateHandle?.get<String>("result")
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
                    // Texto del título
                    Text(stringResource(id = R.string.Datos_pelicula))
                },
                // Ícono de navegación en la izquierda (botón de retroceso)
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
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
            verticalArrangement = Arrangement.Center // Centra verticalmente
        ) {
            Text(
                text = stringResource(id = R.string.Datos_pelicula),
                modifier = Modifier.padding(4.dp),
            )
            // El nombre de la película llega por parámetro, el cual uso para crear un texto
            Text(
                text = nombrePeli,
                modifier = Modifier.padding(top = 8.dp, bottom = 16.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            // el botón de pelicula relacionada debe tener el parámetro de nombrePeli
            ButtonPelRel(navController, nombrePeli)
            Spacer(modifier = Modifier.height(16.dp))
            ButtonEditar(navController, nombrePeli)
            Spacer(modifier = Modifier.height(16.dp))
            ButtonReturn(navController)
            Spacer(modifier = Modifier.height(16.dp))
            // compruebo si la pelicula ha sido editada o no
            result?.let {
                Text(
                    text = if (it == "RESULT_OK") stringResource(id = R.string.editada)
                    else stringResource(id = R.string.cancelado)
                )
            }
        }
    }
}

