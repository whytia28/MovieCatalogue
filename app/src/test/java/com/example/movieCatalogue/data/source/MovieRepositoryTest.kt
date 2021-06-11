package com.example.movieCatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.movieCatalogue.data.source.remote.response.ListMovieResponse
import com.example.movieCatalogue.data.source.remote.response.MovieResponse
import com.example.movieCatalogue.network.ApiService
import com.example.movieCatalogue.utils.DataMovie
import com.google.gson.Gson
import com.nhaarman.mockitokotlin2.doAnswer
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import retrofit2.Callback
import java.lang.NullPointerException

class MovieRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val apiService = Mockito.mock(ApiService::class.java)
    private val movieRepository = FakeMovieRepository(apiService)

    private val movieResponse = DataMovie.generateDataMovie()
    private val movieId = movieResponse[2].movieId
    private val tvShowResponse = DataMovie.generateDataTvShow()
    private val tvShowId = tvShowResponse[2].tvShowId




    @Test
    fun getAllMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as Callback<ListMovieResponse>)
        }
    }

    @Test
    fun getAllTvShow() {
    }

    @Test
    fun getMovieDetail() {
    }

    @Test
    fun getTvShowDetail() {
    }
}