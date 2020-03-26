package com.example.filmesfavoritos.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmesfavoritos.R
import com.example.filmesfavoritos.model.Movie
import com.example.filmesfavoritos.model.MovieHttp
import com.example.filmesfavoritos.ui.MovieDetailActivity
import com.example.filmesfavoritos.ui.adapter.MovieListAdapter
import com.example.filmesfavoritos.ui.viewmodel.MovieListViewModel
import kotlinx.android.synthetic.main.fragment_movie_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher

class MovieListFragment: Fragment() {
    val viewModel: MovieListViewModel by lazy {
        ViewModelProvider(this).get(MovieListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(), layoutManager.orientation)
        )
        viewModel.state.observe(viewLifecycleOwner, Observer { state ->
            when(state) {
                is MovieListViewModel.State.Loading -> {
                    vwLoading.visibility = View.VISIBLE
                }
                is MovieListViewModel.State.Loaded -> {
                    vwLoading.visibility = View.GONE
                    recyclerView.adapter = MovieListAdapter(state.itens, this::openMovieDetail)
                }
                is MovieListViewModel.State.Error -> {
                    vwLoading.visibility = View.GONE
                    if(!state.hasConsumed){
                        state.hasConsumed = true
                        Toast.makeText(requireContext(), R.string.error_loading, Toast.LENGTH_SHORT).show()
                    }
                }
            }

        })
        viewModel.loadMovies()
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.search(query)
                    return true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun openMovieDetail(movie: Movie) {
        lifecycleScope.launch {
            val result = withContext(Dispatchers.IO) {
                MovieHttp.searchMovieByTitle(movie.imdbID)
            }
            result?.let {
                MovieDetailActivity.open(requireContext(), it)}
        }


    }

}