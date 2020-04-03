package com.example.filmesfavoritos.model

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.Request
import java.lang.Exception
import java.util.concurrent.TimeUnit

object MovieHttp {

    private const val API_KEY =
        "e8874a18"
    private const val MOVIE_JSON_URL =
        "https://www.omdbapi.com/?s=%s&apikey=$API_KEY"
    private const val MOVIEBYTITLE_JSON_URL =
        "https://www.omdbapi.com/?i=%s&apikey=$API_KEY"

    private val client = OkHttpClient.Builder()
        .readTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(5, TimeUnit.SECONDS)
        .build()

    fun searchMovie(q: String): SearchResult? {
        val request = Request.Builder()
            .url(String.format(MOVIE_JSON_URL, q))
            .build()

        try {
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            return Gson().fromJson(json, SearchResult::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }

    fun searchMovieByTitle(q: String): Movie? {
        val request = Request.Builder()
            .url(String.format(MOVIEBYTITLE_JSON_URL, q))
            .build()

        try {
            val response = client.newCall(request).execute()
            val json = response.body?.string()
            return Gson().fromJson(json, Movie::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return null
    }
}