package com.example.filmesfavoritos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.filmesfavoritos.repository.MovieRepository

class MovieFavoritesViewModel(
    repository: MovieRepository
) : ViewModel() {

    val favoritesMovies = repository.allFavorites().asLiveData()
}