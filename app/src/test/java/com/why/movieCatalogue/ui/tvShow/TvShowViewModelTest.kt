package com.why.movieCatalogue.ui.tvShow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.why.movieCatalogue.data.MovieCatalogueRepository
import com.why.movieCatalogue.data.source.local.entity.TvShowEntity
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
class TvShowViewModelTest : KoinTest {
    private val viewModel by inject<TvShowViewModel>()

    @Mock
    private lateinit var movieObserver: Observer<Resource<PagedList<TvShowEntity>>>

    @Mock
    private lateinit var pagedList: PagedList<TvShowEntity>

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
        val dummyTvSHow = Resource.success(pagedList)
        `when`(dummyTvSHow.data?.size).thenReturn(8)
        val tvShows = MutableLiveData<Resource<PagedList<TvShowEntity>>>()
        tvShows.value = dummyTvSHow

        `when`(movieRepository.getAllTvShow("ASCEND")).thenReturn(tvShows)
        val tvShowEntity = viewModel.getTvShows("ASCEND").value
        verify(movieRepository).getAllTvShow("ASCEND")

        assertNotNull(tvShowEntity)
        assertEquals(8, tvShowEntity?.data?.size)

        viewModel.getTvShows("ASCEND").observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyTvSHow)
    }
}