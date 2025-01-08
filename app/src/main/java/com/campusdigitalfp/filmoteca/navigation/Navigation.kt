package com.campusdigitalfp.filmoteca.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.campusdigitalfp.filmoteca.screens.AboutScreen
import com.campusdigitalfp.filmoteca.screens.FilmDataScreen
import com.campusdigitalfp.filmoteca.screens.FilmEditScreen
import com.campusdigitalfp.filmoteca.screens.FilmListScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()


    NavHost(navController = navController, startDestination = "list") {

        // NAVEGACIÓN A ABOUT
        composable("about") { AboutScreen(navController) }

        // NAVEGACION A FILMDATA
        // Modificación para que se pueda pasar el indice de la pelicula seleccionada
        composable("film/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt()
            if (id != null) {
                FilmDataScreen(navController, id)
            }
        }

        // NAVEGACION A FILMEDIT
        // Modificación para que se pueda pasar el parámetro de la película a la pantalla de editar
        composable("edit/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toInt()
            if (id != null) {
                FilmEditScreen(navController, id)
            }
        }

        // NAVEGACION A FILMLIST
        composable("list") { FilmListScreen(navController) }
    }
}