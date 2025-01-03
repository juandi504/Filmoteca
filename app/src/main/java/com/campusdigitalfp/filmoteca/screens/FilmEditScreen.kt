package com.campusdigitalfp.filmoteca.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
fun ButtonGuardar(navController: NavHostController) {

    Button(
        onClick = { navController.popBackStack() },
    ) {
        Text(stringResource(id = R.string.Guardar))
    }
}

@Composable
fun ButtonCancelar(navController: NavHostController) {

    Button(
        onClick = { navController.popBackStack() },
    ) {
        Text(stringResource(id = R.string.Cancelar))
    }
}

@Composable
fun FilmEditScreen(navController: NavHostController) {
    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center // Centra verticalmente
        ) {
            Text(
                text = stringResource(id = R.string.Editar_pelicula),
                modifier = Modifier.padding(16.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            ButtonGuardar(navController)
            Spacer(modifier = Modifier.height(16.dp))
            ButtonCancelar(navController)

        }
    }
}

