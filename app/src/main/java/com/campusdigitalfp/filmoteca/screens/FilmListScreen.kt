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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R


@Composable
fun ButtonPelA(navController: NavHostController) {

    Button(
        onClick = { navController.navigate("data/Película A") },
    ) {
        Text(stringResource(id = R.string.Ver_PeliculaA))
    }
}

@Composable
fun ButtonPelB(navController: NavHostController) {

    Button(
        onClick = { navController.navigate("data/Película B") },
    ) {
        Text(stringResource(id = R.string.Ver_PeliculaB))
    }
}

@Composable
fun ButtonAbout(navController: NavHostController) {

    Button(
        onClick = { navController.navigate("about") },
    ) {
        Text(stringResource(id = R.string.Acerca_de))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FilmListScreen(navController: NavHostController) {
    Scaffold (
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
    ){ paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Centra verticalmente
        ) {
            ButtonPelA(navController)
            Spacer(modifier = Modifier.height(16.dp))
            ButtonPelB(navController)
            Spacer(modifier = Modifier.height(16.dp))
            ButtonAbout(navController)
        }
    }
}


