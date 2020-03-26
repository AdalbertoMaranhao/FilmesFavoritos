package com.example.filmesfavoritos.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    val Title: String,
    val Year: String?,
    val imdbID: String,
    val Type: String?,
    val Plot: String?,
    val Poster: String?
): Parcelable