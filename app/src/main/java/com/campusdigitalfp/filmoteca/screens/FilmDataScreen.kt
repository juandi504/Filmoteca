package com.campusdigitalfp.filmoteca.screens

import android.content.Context
import android.widget.Toast
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
fun ButtonPelRel(navController: NavHostController) {

    Button(
        onClick = { navController.navigate("data") },
    ) {
        Text(stringResource(id = R.string.ver_pelicula_rel))
    }
}

@Composable
fun ButtonEditar(navController: NavHostController) {
    val context = LocalContext.current // Obtener el contexto antes de usarlo
    val toastMessage = stringResource(id = R.string.toast)
    Button(
        onClick = { navController.navigate("edit") },
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


@Composable
fun FilmDataScreen(navController: NavHostController) {
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
                text = stringResource(id = R.string.Datos_pelicula),
                modifier = Modifier.padding(16.dp),
            )
            Spacer(modifier = Modifier.height(16.dp))
            ButtonPelRel(navController)
            Spacer(modifier = Modifier.height(16.dp))
            ButtonEditar(navController)
            Spacer(modifier = Modifier.height(16.dp))
            ButtonReturn(navController)
        }
    }
}

