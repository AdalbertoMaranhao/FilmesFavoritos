package com.example.filmesfavoritos.repository

import com.example.filmesfavoritos.model.Movie

object FilmeMovieMapper {
    fun filmeToMovie(filme: Filme) =
        Movie(
            filme.Title,
            filme.Year,
            filme.imdbID,
            filme.Type,
            filme.Plot,
            filme.Poster
        )

    fun movieToFilme(movie: Movie) =
        Filme(
            movie.Title,
            movie.Year,
            movie.imdbID,
            movie.Type,
            movie.Plot,
            movie.Poster
        )
}