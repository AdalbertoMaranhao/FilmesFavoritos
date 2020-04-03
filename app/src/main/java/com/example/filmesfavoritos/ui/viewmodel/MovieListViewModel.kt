package com.example.filmesfavoritos.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.filmesfavoritos.model.Movie
import com.example.filmesfavoritos.model.MovieHttp
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.lang.Exception

class MovieListViewModel : ViewModel() {
    private val _state = MutableLiveData<State>()
    val state: LiveData<State>
        get() = _state

    fun loadMovies() {
        if (_state.value != null) return
        search("avengers")
    }

    fun search(query: String) {
        viewModelScope.launch {
            _state.value = State.Loading

            val result = withContext(Dispatchers.IO) {
                MovieHttp.searchMovie(query)
            }
            if (result?.Search == null) {
                _state.value = State.Error(Exception("Error Loading"), false)
            } else {
                _state.value = State.Loaded(result.Search)
            }

        }
    }

    sealed class State {
        object Loading : State()
        data class Loaded(val itens: List<Movie>) : State()
        data class Error(val e: Throwable, var hasConsumed: Boolean) : State()
    }
}