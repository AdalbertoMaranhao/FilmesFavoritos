package com.example.filmesfavoritos.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.filmesfavoritos.R
import com.example.filmesfavoritos.model.Movie
import com.example.filmesfavoritos.model.MovieHttp
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.item_movie.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MovieListAdapter(
    private val itens: List<Movie>,
    private val onItemClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieListAdapter.MovieHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieHolder {
        val layout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_movie, parent, false)
        return MovieHolder(layout)
    }

    override fun getItemCount(): Int = itens.size

    override fun onBindViewHolder(holder: MovieHolder, position: Int) {
        val movie = itens[position]
        if (movie.Poster != null) {
            Picasso.get().load(movie.Poster).into(holder.imgPoster)
        } else {
            holder.imgPoster.setImageResource(R.drawable.ic_broken_image)
        }
        holder.txtTitle.text = movie.Title
        holder.txtYear.text = movie.Year
        holder.itemView.setOnClickListener {
           onItemClick(movie)
        }

    }

    class MovieHolder(rootView: View) : RecyclerView.ViewHolder(rootView) {
        val imgPoster: ImageView = rootView.imgPoster
        val txtTitle: TextView = rootView.txtTitle
        val txtYear: TextView = rootView.txtYear
    }
}