package com.example.filmesfavoritos

import com.example.filmesfavoritos.model.MovieHttp
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun apiTest() {
        val searchResult = MovieHttp.searchMovieByTitle("lisbela")
        println(searchResult?.Plot)

    }
}
