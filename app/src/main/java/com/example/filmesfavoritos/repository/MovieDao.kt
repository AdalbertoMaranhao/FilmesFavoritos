package com.example.filmesfavoritos.repository

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(filme: Filme): Long

    @Delete
    suspend fun delete(filme: Filme): Int

    @Query("SELECT * FROM Filme")
    fun allFavorites(): Flow<List<Filme>>

    @Query("SELECT COUNT(imdbID) FROM Filme WHERE imdbID = :id")
    suspend fun isFavorite(id: String): Int

}