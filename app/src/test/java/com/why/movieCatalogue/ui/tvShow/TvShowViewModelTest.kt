package com.why.movieCatalogue.ui.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.why.movieCatalogue.data.source.local.entity.TvShowEntity
import com.why.movieCatalogue.data.MovieCatalogueRepository
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
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest : KoinTest {
    private val viewModel by inject<TvShowViewModel>()

    @Mock
    private lateinit var movieObserver: Observer<List<TvShowEntity>>

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
    fun getTvShows() {
        val movieRepository = declareMock<MovieCatalogueRepository>()
        val dummyTvSHow = DataMovie.generateDataTvShow()
        val tvShows = MutableLiveData<List<TvShowEntity>>()
        tvShows.value = dummyTvSHow

        `when`(movieRepository.getAllTvShow()).thenReturn(tvShows)
        val tvShowEntity = viewModel.getTvShows().value
        verify(movieRepository).getAllTvShow()

        assertNotNull(tvShowEntity)
        assertEquals(12, tvShowEntity?.size)

        viewModel.getTvShows().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyTvSHow)
    }
}