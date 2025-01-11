package com.campusdigitalfp.filmoteca

import com.campusdigitalfp.filmoteca.FilmDataSource.films

// Clase que crea una película nueva con datos por defecto y la añade a la lista mutable
class CreateFilm() {

    fun nuevaPelicula(){
        val nuevaPelicula = Film()

        // Elimino el crear un id ya que se genera automáticamente cuando crea una nueva instancia de Film
        nuevaPelicula.title = "Película por defecto"
        nuevaPelicula.director = "Director desconocido"
        nuevaPelicula.imageResId = R.drawable.pelidefecto
        nuevaPelicula.comments = ""
        nuevaPelicula.format = Film.FORMAT_DVD
        nuevaPelicula.genre = Film.GENRE_ACTION
        nuevaPelicula.imdbUrl = ""
        nuevaPelicula.year = 0
        films.add(nuevaPelicula) // Añadir a la lista mutable
    }
}