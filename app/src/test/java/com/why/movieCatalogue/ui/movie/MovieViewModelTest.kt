package com.why.movieCatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.why.movieCatalogue.data.MovieCatalogueRepository
import com.why.movieCatalogue.data.source.local.entity.MovieEntity
import com.why.movieCatalogue.utils.appModule
import com.why.movieCatalogue.vo.Resource
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
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest : KoinTest {
    private val viewModel by inject<MovieViewModel>()

    @Mock
    private lateinit var movieObserver: Observer<Resource<PagedList<MovieEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

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
        val movieRepository = declareMock<MovieCatalogueRepository>()
        val dummyMovie = Resource.success(pagedList)
        `when`(dummyMovie.data?.size).thenReturn(8)
        val movies = MutableLiveData<Resource<PagedList<MovieEntity>>>()
        movies.value = dummyMovie

        `when`(movieRepository.getAllMovie("ASCEND")).thenReturn(movies)
        val movieEntities = viewModel.getMovies("ASCEND").value
        verify(movieRepository).getAllMovie("ASCEND")
        assertNotNull(movieEntities)
        assertEquals(8, movieEntities?.data?.size)

        viewModel.getMovies("ASCEND").observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }
}