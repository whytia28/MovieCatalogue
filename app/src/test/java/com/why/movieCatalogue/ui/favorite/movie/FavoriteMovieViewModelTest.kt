package com.why.movieCatalogue.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.why.movieCatalogue.data.MovieCatalogueRepository
import com.why.movieCatalogue.data.source.local.entity.MovieEntity
import com.why.movieCatalogue.utils.DataMovie
import com.why.movieCatalogue.utils.appModule
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
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieViewModelTest : KoinTest {
    private val viewModel by inject<FavoriteMovieViewModel>()

    @Mock
    private lateinit var movieObserver: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @get:Rule
    val mockProvider = MockProviderRule.create { clazz ->
        mock(clazz.java)
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
    fun getFavMovies() {
        val movieRepository = declareMock<MovieCatalogueRepository>()
        val dummyMovie = pagedList
        `when`(dummyMovie.size).thenReturn(8)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovie

        `when`(movieRepository.getFavoriteMovie()).thenReturn(movies)
        val movie = viewModel.getFavMovie().value
        verify(movieRepository).getFavoriteMovie()
        assertNotNull(movie)
        assertEquals(8, movie?.size)

        viewModel.getFavMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun setFavMovie() {
        val movieRepository = declareMock<MovieCatalogueRepository>()
        viewModel.setFavMovie(DataMovie.getDetailMovie())
        verify(movieRepository).setFavoriteMovie(DataMovie.getDetailMovie(), true)
        verifyNoMoreInteractions(movieRepository)
    }
}