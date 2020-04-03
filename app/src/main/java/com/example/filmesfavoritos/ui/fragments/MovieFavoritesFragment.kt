package com.example.filmesfavoritos.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.filmesfavoritos.R
import com.example.filmesfavoritos.model.Movie
import com.example.filmesfavoritos.repository.MovieRepository
import com.example.filmesfavoritos.ui.MovieDetailActivity
import com.example.filmesfavoritos.ui.adapter.MovieListAdapter
import com.example.filmesfavoritos.ui.viewmodel.MovieFavoritesViewModel
import com.example.filmesfavoritos.ui.viewmodel.MovieVmFactory
import kotlinx.android.synthetic.main.fragment_movie_list.*

class MovieFavoritesFragment : Fragment() {
    val viewModel: MovieFavoritesViewModel by lazy {
        ViewModelProvider(
            this,
            MovieVmFactory(
                MovieRepository(requireContext())
            )
        ).get(MovieFavoritesViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_movie_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView.visibility = View.GONE
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        recyclerView.addItemDecoration(
            DividerItemDecoration(requireContext(), layoutManager.orientation)
        )
        viewModel.favoritesMovies.observe(viewLifecycleOwner, Observer { itens ->
            vwLoading.visibility = View.GONE
            recyclerView.adapter = MovieListAdapter(itens, this::openMovieDetail)
        })
    }

    private fun openMovieDetail(movie: Movie) {
        MovieDetailActivity.open(requireContext(), movie)
    }

}