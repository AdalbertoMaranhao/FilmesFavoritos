package com.example.filmesfavoritos.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.*
import com.example.filmesfavoritos.R
import com.example.filmesfavoritos.model.Movie
import com.example.filmesfavoritos.model.MovieHttp
import com.example.filmesfavoritos.repository.MovieRepository
import com.example.filmesfavoritos.ui.viewmodel.MovieDetailViewModel
import com.example.filmesfavoritos.ui.viewmodel.MovieFavoritesViewModel
import com.example.filmesfavoritos.ui.viewmodel.MovieVmFactory
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieDetailActivity : AppCompatActivity() {
    val viewModel: MovieDetailViewModel by lazy {
        ViewModelProvider(
            this,
            MovieVmFactory(
                MovieRepository(this)
            )
        ).get(MovieDetailViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        val movie = intent.getParcelableExtra<Movie>(EXTRA_MOVIE)
        if (movie != null) {

            if (movie.Poster != null) {
                Picasso.get().load(movie.Poster).into(imgPoster)
            } else {
                imgPoster.setImageResource(R.drawable.ic_broken_image)
            }
            txtTitle.text = movie.Title
            txtYear.text = movie.Year
            txtDescription.text = movie.Plot

            viewModel.isFavorite.observe(
                this@MovieDetailActivity, Observer { isFavorite ->
                    if (isFavorite) {
                        fabFavorites.setImageResource(R.drawable.ic_delete)
                        fabFavorites.setOnClickListener {
                            viewModel.removeFromFavorites(movie)
                        }
                    } else {
                        fabFavorites.setImageResource(R.drawable.ic_add)
                        fabFavorites.setOnClickListener {
                            viewModel.saveToFavorites(movie)
                        }
                    }
                }
            )
            viewModel.onCreate(movie)
            //faz uma nova busca e carrega as informações do filme selecionado, como a descrição

        } else {
            finish()
        }
    }

    companion object {
        private const val EXTRA_MOVIE = "movie"

        fun open(context: Context, movie: Movie) {
            val detailIntent = Intent(context, MovieDetailActivity::class.java)
            detailIntent.putExtra(EXTRA_MOVIE, movie)
            context.startActivity(detailIntent)
        }
    }
}
