package com.campusdigitalfp.filmoteca.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.campusdigitalfp.filmoteca.FilmDataSource
import com.campusdigitalfp.filmoteca.screens.AboutScreen
import com.campusdigitalfp.filmoteca.screens.FilmDataScreen
import com.campusdigitalfp.filmoteca.screens.FilmEditScreen
import com.campusdigitalfp.filmoteca.screens.FilmListScreen
import java.util.UUID

@Composable
fun Navigation() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "list") {

        // NAVEGACIÓN A ABOUT
        composable("about") { AboutScreen(navController) }

        // NAVEGACION A FILMDATA
        // Modificación para que se pueda pasar el indice de la pelicula seleccionada
        composable("film/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") // modificación para admitir el UUID
            // Busca la primera película en la lista cuyo identificador (id) coincida con el argumento id
            val film = FilmDataSource.films.find { it.id.toString() == id }
            if (film != null) {
                FilmDataScreen(navController, film.id)
            }else {
                navController.popBackStack()
            }
        }

        // NAVEGACION A FILMEDIT
        // Modificación para que se pueda pasar el parámetro de la película a la pantalla de editar
        composable("edit/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") // modificación para admitir el UUID
            // Busca la primera película en la lista cuyo identificador (id) coincida con el argumento id
            val film = FilmDataSource.films.find { it.id.toString() == id }
            if (film != null) {
                FilmEditScreen(navController, film.id)
            }else {
                navController.popBackStack()
            }
        }

        // NAVEGACION A FILMLIST
        composable("list") { FilmListScreen(navController) }
    }
}