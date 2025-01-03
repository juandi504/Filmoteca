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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.campusdigitalfp.filmoteca.R


@Composable
fun ButtonPelA(navController: NavHostController) {

    Button(
        onClick = { navController.navigate("data") },
    ) {
        Text(stringResource(id = R.string.Ver_PeliculaA))
    }
}

@Composable
fun ButtonPelB(navController: NavHostController) {

    Button(
        onClick = { navController.navigate("data") },
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


@Composable
fun FilmListScreen(navController: NavHostController) {
    Scaffold { paddingValues ->
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


