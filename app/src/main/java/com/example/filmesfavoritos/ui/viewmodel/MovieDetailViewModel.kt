package com.example.filmesfavoritos.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmesfavoritos.model.Movie
import com.example.filmesfavoritos.model.MovieHttp
import com.example.filmesfavoritos.repository.MovieRepository
import kotlinx.coroutines.launch

class MovieDetailViewModel(private val repository: MovieRepository) : ViewModel() {
    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun onCreate(movie: Movie) {
        viewModelScope.launch {
            _isFavorite.value = repository.isFavorite(movie.imdbID)
        }
    }

    fun saveToFavorites(movie: Movie) {
        viewModelScope.launch {
            repository.save(movie)
            _isFavorite.value = repository.isFavorite(movie.imdbID)
        }
    }

    fun removeFromFavorites(movie: Movie) {
        viewModelScope.launch {
            repository.delete(movie)
            _isFavorite.value = repository.isFavorite(movie.imdbID)
        }
    }
}