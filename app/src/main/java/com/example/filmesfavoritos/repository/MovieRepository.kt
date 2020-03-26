package com.example.filmesfavoritos.repository

import android.content.Context
import com.example.filmesfavoritos.model.Movie
import com.example.filmesfavoritos.model.MovieHttp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MovieRepository(context: Context) {
    private val dao: MovieDao = AppDatabase.getDatabase(context).getMovieDao()

    suspend fun save(movie: Movie) {
        dao.save(FilmeMovieMapper.movieToFilme(movie))
    }

    suspend fun delete(movie: Movie) {
        dao.delete(FilmeMovieMapper.movieToFilme(movie))
    }

    suspend fun isFavorite(id: String): Boolean {
        return dao.isFavorite(id) > 0
    }

    fun allFavorites(): Flow<List<Movie>> {
        return dao.allFavorites()
            .map { filmeList ->
                filmeList.map { filme ->
                    FilmeMovieMapper.filmeToMovie(filme)
                }
            }
    }
}