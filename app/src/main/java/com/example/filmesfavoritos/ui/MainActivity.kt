package com.example.filmesfavoritos.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.filmesfavoritos.R
import com.example.filmesfavoritos.ui.adapter.MoviePagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.menu_sign_out) {
            FirebaseAuth.getInstance().signOut()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}
