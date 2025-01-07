package com.campusdigitalfp.filmoteca

data class Film(
    var id: Int = 0,
    var imageResId: Int = 0, // Propiedades de la clase
    var title: String? = null,
    var director: String? = null,
    var year: Int = 0,
    var genre: Int = 0,
    var format: Int = 0,
    var imdbUrl: String? = null,
    var comments: String? = null
) {
    // Se sobreescribe el metodo toString()
    override fun toString(): String {
        // Al convertir a cadena mostramos su título
        return title ?: "<Sin título>"
    }

    // Declaración de constantes de géneros y formatos
    companion object {
        const val FORMAT_DVD = 0 // Formatos
        const val FORMAT_BLURAY = 1
        const val FORMAT_DIGITAL = 2
        const val GENRE_ACTION = 0 // Géneros
        const val GENRE_COMEDY = 1
        const val GENRE_DRAMA = 2
        const val GENRE_SCIFI = 3
        const val GENRE_HORROR = 4

        // Mapeo de géneros a cadenas
        fun getGenreString(genre: Int): String {
            return when (genre) {
                GENRE_ACTION -> "Action"
                GENRE_COMEDY -> "Comedy"
                GENRE_DRAMA -> "Drama"
                GENRE_SCIFI -> "Sci-Fi"
                GENRE_HORROR -> "Horror"
                else -> "Unknown"
            }
        }

        // Mapeo de formatos a cadenas
        fun getformatString(format: Int): String {
            return when (format) {
                FORMAT_DVD -> "DVD"
                FORMAT_BLURAY -> "Blu-Ray"
                FORMAT_DIGITAL -> "Digital"
                else -> "Unknown"
            }
        }
    }
}