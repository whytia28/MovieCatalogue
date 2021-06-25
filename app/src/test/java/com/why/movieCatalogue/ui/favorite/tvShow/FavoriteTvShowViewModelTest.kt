package com.why.movieCatalogue.ui.favorite.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.why.movieCatalogue.data.MovieCatalogueRepository
import com.why.movieCatalogue.data.source.local.entity.TvShowEntity
import com.why.movieCatalogue.utils.DataMovie
import com.why.movieCatalogue.utils.appModule
import org.junit.After
import org.junit.Assert.*
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
class FavoriteTvShowViewModelTest: KoinTest {
    private val viewModel by inject<FavoriteTvShowViewModel>()

    @Mock
    private lateinit var tvShowObserver: Observer<PagedList<TvShowEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

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
    fun getFavTvShow() {
        val movieRepository = declareMock<MovieCatalogueRepository>()
        val dummyTvShow = pagedList
        `when`(dummyTvShow.size).thenReturn(8)
        val tvShows = MutableLiveData<PagedList<TvShowEntity>>()
        tvShows.value = dummyTvShow

        `when`(movieRepository.getFavoriteTvShow()).thenReturn(tvShows)
        val tvShow = viewModel.getFavTvShow().value
        verify(movieRepository).getFavoriteTvShow()
        assertNotNull(tvShow)
        assertEquals(8, tvShow?.size)

        viewModel.getFavTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyTvShow)
    }

    @Test
    fun setFavTvShow() {
        val movieRepository = declareMock<MovieCatalogueRepository>()
        viewModel.setFavTvShow(DataMovie.getDetailTvShow())
        verify(movieRepository).setFavoriteTvShow(DataMovie.getDetailTvShow(), true)
        verifyNoMoreInteractions(movieRepository)
    }
}