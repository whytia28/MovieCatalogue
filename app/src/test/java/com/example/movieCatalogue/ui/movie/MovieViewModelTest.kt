package com.example.movieCatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.movieCatalogue.data.MovieEntity
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
class MovieViewModelTest : KoinTest {
    private val viewModel by inject<MovieViewModel>()

    @Mock
    private lateinit var movieObserver: Observer<List<MovieEntity>>

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
    fun getMovies() {
        val movieRepository = declareMock<MovieRepository>()
        val dummyMovie = DataMovie.generateDataMovie()
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovie

        `when`(movieRepository.getAllMovie()).thenReturn(movies)
        val movieEntities = viewModel.getMovies().value
        verify(movieRepository).getAllMovie()
        assertNotNull(movieEntities)
        assertEquals(12, movieEntities?.size)

        viewModel.getMovies().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }
}