package com.example.filmesfavoritos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.filmesfavoritos.R
import com.example.filmesfavoritos.ui.adapter.MoviePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewPager.adapter = MoviePagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setText(
                if (position == 0)
                    R.string.tab_movies
                else
                    R.string.tab_favorites
            )
        }.attach()
    }

}
