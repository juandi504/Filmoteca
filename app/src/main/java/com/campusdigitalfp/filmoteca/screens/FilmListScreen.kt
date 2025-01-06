package com.campusdigitalfp.filmoteca.screens


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
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
        // LazyColumn
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp),
            //verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            // Iteramos cada elemento que tiene la lista mutable de FilmDataSource
            // y mediante la función lambda usamos para cada elemento la función toString()
            items(FilmDataSource.films) { film ->
                Text(
                    text = film.toString(), // Usando el método toString() de Film
                    modifier = Modifier.padding(vertical = 8.dp)
                )
            }
        }
    }
}
