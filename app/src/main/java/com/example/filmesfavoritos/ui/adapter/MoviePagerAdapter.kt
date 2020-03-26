package com.example.filmesfavoritos.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.filmesfavoritos.ui.fragments.MovieFavoritesFragment
import com.example.filmesfavoritos.ui.fragments.MovieListFragment

class MoviePagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0)
            MovieListFragment()
        else
            MovieFavoritesFragment()
    }

}