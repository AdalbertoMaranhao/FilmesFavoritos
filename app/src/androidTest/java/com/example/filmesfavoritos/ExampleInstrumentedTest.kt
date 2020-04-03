package com.example.filmesfavoritos

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.filmesfavoritos.repository.AppDatabase
import com.example.filmesfavoritos.repository.Filme
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext

        val db = AppDatabase.getDatabase(appContext)
        val dao = db.getMovieDao()

        runBlocking {
            val movieTeste = Filme(
                "teste",
                "2020",
                "1234",
                "movie",
                "hdfgnidongisd",
                "teste"
            )
            val rowId = dao.save(movieTeste)
            assertTrue(rowId > -1)

            val movies = dao.allFavorites().first()
            movies.forEach {
                assertEquals(it.Title, "teste")
            }

            val isFavorite = dao.isFavorite("1234")
            assertTrue(isFavorite == 1)

            val result = dao.delete(movieTeste)
            assertTrue(result == 1)
        }
    }
}
