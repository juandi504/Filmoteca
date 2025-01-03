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
        composable("about") { AboutScreen(navController) }

        // Modificación para que se pueda pasar el parámetro de la película
        composable("data/{nombrePeli}") { backStackEntry ->
            val nombrePeli = backStackEntry.arguments?.getString("nombrePeli")?: "Desconocida"
            FilmDataScreen(navController, nombrePeli)
        }
        // Modificación para que se pueda pasar el parámetro de la película a la pantalla de editar
        composable("edit/{nombrePeli}") { backStackEntry ->
            val nombrePeli = backStackEntry.arguments?.getString("nombrePeli")?: "Desconocida"
            FilmEditScreen(navController, nombrePeli)
        }
        composable("list") { FilmListScreen(navController) }
    }
}