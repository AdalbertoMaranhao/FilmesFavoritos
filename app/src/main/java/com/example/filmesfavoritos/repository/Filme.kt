package com.example.filmesfavoritos.repository

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Filme(
    val Title: String,
    val Year: String?,
    @PrimaryKey
    val imdbID: String,
    val Type: String?,
    val Plot: String?,
    val Poster: String?
)