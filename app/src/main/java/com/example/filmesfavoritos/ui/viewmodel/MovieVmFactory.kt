package com.example.filmesfavoritos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.filmesfavoritos.repository.MovieRepository
import java.lang.IllegalArgumentException

class MovieVmFactory(val repository: MovieRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom (MovieFavoritesViewModel::class.java)){
            return MovieFavoritesViewModel(repository) as T
        } else if (modelClass.isAssignableFrom (MovieDetailViewModel::class.java)){
            return MovieDetailViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknow ViewModel class")
    }
}