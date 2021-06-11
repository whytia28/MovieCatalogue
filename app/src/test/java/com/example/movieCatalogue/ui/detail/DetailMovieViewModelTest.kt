package com.example.movieCatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieCatalogue.data.MovieEntity
import com.example.movieCatalogue.data.TvShowEntity
import com.example.movieCatalogue.data.source.MovieRepository
import com.example.movieCatalogue.utils.DataMovie
import com.example.movieCatalogue.utils.appModule
import com.nhaarman.mockitokotlin2.verify
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import org.koin.test.mock.MockProviderRule
import org.koin.test.mock.declareMock
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest : KoinTest {

    private val dummyMovie = DataMovie.generateDataMovie()[2]
    private val dummyTvShow = DataMovie.generateDataTvShow()[2]
    private val movieId = dummyMovie.movieId
    private val tvShowId = dummyTvShow.tvShowId

    private val viewModel by inject<DetailMovieViewModel>()

    @Mock
    private lateinit var movieObserver: Observer<MovieEntity>

    @Mock
    private lateinit var tvShowObserver: Observer<TvShowEntity>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        Mockito.mock(clazz.java)
    }

    @Before
    fun before() {
        startKoin {
            modules(appModule)
        }
    }

    @After
    fun after() {
        stopKoin()
    }


    @Test
    fun getMovieDetail() {
        val movieRepository = declareMock<MovieRepository>()
        val movieDetail = MutableLiveData<MovieEntity>()
        movieDetail.value = dummyMovie

        viewModel.setSelectedMovie(movieId)
        `when`(movieRepository.getMovieDetail(movieId)).thenReturn(movieDetail)
        val movie = viewModel.getMovie().value
        verify(movieRepository).getMovieDetail(movieId)
        assertNotNull(movie)
        assertEquals(movie, dummyMovie)

        viewModel.getMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)

    }

    @Test
    fun getTvShowDetail() {
        val movieRepository = declareMock<MovieRepository>()
        val tvShowDetail = MutableLiveData<TvShowEntity>()
        tvShowDetail.value = dummyTvShow

        viewModel.setSelectedTvShow(tvShowId)
        `when`(movieRepository.getTvShowDetail(tvShowId)).thenReturn(tvShowDetail)
        val tvShow = viewModel.getTvShow().value
        verify(movieRepository).getTvShowDetail(tvShowId)
        assertNotNull(tvShow)
        assertEquals(tvShow, dummyTvShow)

        viewModel.getTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }
}