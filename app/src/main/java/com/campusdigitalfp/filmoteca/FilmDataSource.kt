package com.campusdigitalfp.filmoteca

// Aquí se crea el object de Kotlin con la lista de las películas y sus datos.
object FilmDataSource {
    // Lista mutable de objetos Film
    val films: MutableList<Film> = mutableListOf()

    // Con init inicializamos los datos y se añaden a la lista films
    init {
        // Primera película: Harry Potter y la piedra filosofal
        val f1 = Film()
        f1.id = films.size
        f1.title = "Harry Potter y la piedra filosofal"
        f1.director = "Chris Columbus"
        f1.imageResId = R.drawable.harry
        f1.comments = "Una aventura mágica en Hogwarts."
        f1.format = Film.FORMAT_DVD
        f1.genre = Film.GENRE_ACTION // Cambia según corresponda
        f1.imdbUrl = "http://www.imdb.com/title/tt0241527"
        f1.year = 2001
        films.add(f1)

        // Segunda película: Regreso al futuro
        val f2 = Film()
        f2.id = films.size
        f2.title = "Regreso al futuro"
        f2.director = "Robert Zemeckis"
        f2.imageResId = R.drawable.regreso
        f2.comments = "Una comedia de ciencia-ficción salvajemente entretenida"
        f2.format = Film.FORMAT_DIGITAL
        f2.genre = Film.GENRE_SCIFI
        f2.imdbUrl = "http://www.imdb.com/title/tt0088763"
        f2.year = 1985
        films.add(f2)

        // Tercera película: El rey león
        val f3 = Film()
        f3.id = films.size
        f3.title = "El rey león"
        f3.director = "Roger Allers, Rob Minkoff"
        f3.imageResId = R.drawable.leon
        f3.comments = "Una historia de crecimiento y responsabilidad."
        f3.format = Film.FORMAT_BLURAY
        f3.genre = Film.GENRE_ACTION // Cambia según corresponda
        f3.imdbUrl = "http://www.imdb.com/title/tt0110357"
        f3.year = 1994
        films.add(f3)

        // Cuarta película: Snatch cerdos y diamantes
        val f4 = Film()
        f4.id = films.size
        f4.title = "Snatch cerdos y diamantes"
        f4.director = "Guy Ritchie"
        f4.imageResId = R.drawable.snatch
        f4.comments = "Película de culto espectacular."
        f4.format = Film.FORMAT_BLURAY
        f4.genre = Film.GENRE_ACTION // Cambia según corresponda
        f4.imdbUrl = "https://www.imdb.com/es-es/title/tt0208092/"
        f4.year = 2000
        films.add(f4)

        // Quinta película: La vida es bella
        val f5 = Film()
        f5.id = films.size
        f5.title = "La vida es bella"
        f5.director = "Roberto Benigni"
        f5.imageResId = R.drawable.bella
        f5.comments = "Esta es una historia sencilla, pero no es fácil contarla."
        f5.format = Film.FORMAT_DVD
        f5.genre = Film.GENRE_DRAMA // Cambia según corresponda
        f5.imdbUrl = "https://www.imdb.com/es-es/title/tt0118799/"
        f5.year = 1997
        films.add(f5)

        // Sexta película: Forrest Gump
        val f6 = Film()
        f6.id = films.size
        f6.title = "Forrest Gump"
        f6.director = "Robert Zemeckis"
        f6.imageResId = R.drawable.forrest
        f6.comments = "Yo no sé mucho de casi nada."
        f6.format = Film.FORMAT_BLURAY
        f6.genre = Film.GENRE_COMEDY // Cambia según corresponda
        f6.imdbUrl = "https://www.imdb.com/es-es/title/tt0109830/"
        f6.year = 1994
        films.add(f6)

        // Séptima película: Pulp Fiction
        val f7 = Film()
        f7.id = films.size
        f7.title = "Pulp Fiction"
        f7.director = "Quentin Tarantino"
        f7.imageResId = R.drawable.pulp
        f7.comments = "Obra maestra"
        f7.format = Film.FORMAT_DVD
        f7.genre = Film.GENRE_ACTION // Cambia según corresponda
        f7.imdbUrl = "https://www.imdb.com/es-es/title/tt0110912/"
        f7.year = 1994
        films.add(f7)
    }
}