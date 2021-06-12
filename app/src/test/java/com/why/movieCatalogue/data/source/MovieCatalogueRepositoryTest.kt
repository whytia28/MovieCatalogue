package com.why.movieCatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import com.why.movieCatalogue.data.source.remote.RemoteDataSource
import com.why.movieCatalogue.data.source.remote.response.MovieResponse
import com.why.movieCatalogue.data.source.remote.response.ResultMovie
import com.why.movieCatalogue.data.source.remote.response.ResultTvShow
import com.why.movieCatalogue.data.source.remote.response.TvShowResponse
import com.why.movieCatalogue.utils.LiveDataTestUtil
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class MovieCatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remoteData = mock(RemoteDataSource::class.java)
    private val movieRepository = FakeMovieCatalogueRepository(remoteData)

    private val movieResponse: List<ResultMovie> = ArrayList()
    private val movieDetail = mock(MovieResponse::class.java)
    private val movieId = movieDetail.id
    private val tvShowResponse: List<ResultTvShow> = ArrayList()
    private val tvShowDetail = mock(TvShowResponse::class.java)
    private val tvShowId = tvShowDetail.id

    @Test
    fun getAllMovie() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadMovieCallback)
                .onAllMovieReceived(movieResponse)
            null
        }.`when`(remoteData).getAllMovie(any())
        val movieEntities = LiveDataTestUtil.getValue(movieRepository.getAllMovie())
        verify(remoteData).getAllMovie(any())

        assertNotNull(movieEntities)
        assertEquals(movieResponse.size.toDouble(), movieEntities.size.toDouble())
    }

    @Test
    fun getAllTvShow() {
        doAnswer {
            (it.arguments[0] as RemoteDataSource.LoadTvShowCallback)
                .onAllTvShowReceived(tvShowResponse)
            null
        }.`when`(remoteData).getAllTvShow(any())
        val tvShowEntities = LiveDataTestUtil.getValue(movieRepository.getAllTvShow())
        verify(remoteData).getAllTvShow(any())

        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponse.size.toDouble(), tvShowEntities.size.toDouble())
    }

    @Test
    fun getMovieDetail() {
        doAnswer {
            (it.arguments[1] as RemoteDataSource.MovieDetailCallback)
                .onMovieDetailReceived(movieDetail)
            null
        }.`when`(remoteData).getMovieDetail(eq(movieId), any())
        val movieDetails = LiveDataTestUtil.getValue(movieRepository.getMovieDetail(movieId))
        verify(remoteData).getMovieDetail(eq(movieId), any())

        assertNotNull(movieDetail)
        assertEquals(movieDetails.movieId, movieDetail.id)
    }

    @Test
    fun getTvShowDetail() {
        doAnswer {
            (it.arguments[1] as RemoteDataSource.TvShowDetailCallback)
                .onTvShowDetailReceived(tvShowDetail)
            null
        }.`when`(remoteData).getTvShowDetail(eq(tvShowId), any())
        val tvShowDetails = LiveDataTestUtil.getValue(movieRepository.getTvShowDetail(tvShowId))
        verify(remoteData).getTvShowDetail(eq(tvShowId), any())

        assertNotNull(tvShowDetail)
        assertEquals(tvShowDetails.tvShowId, tvShowDetail.id)
    }
}