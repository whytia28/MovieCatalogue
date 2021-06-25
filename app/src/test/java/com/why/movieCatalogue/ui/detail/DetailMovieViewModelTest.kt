package com.why.movieCatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.why.movieCatalogue.data.MovieCatalogueRepository
import com.why.movieCatalogue.data.source.local.entity.MovieEntity
import com.why.movieCatalogue.data.source.local.entity.TvShowEntity
import com.why.movieCatalogue.ui.detail.DetailMovieViewModel.Companion.MOVIE
import com.why.movieCatalogue.ui.detail.DetailMovieViewModel.Companion.TV_SHOW
import com.why.movieCatalogue.utils.DataMovie
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
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailMovieViewModelTest : KoinTest {

    private val dummyMovie = DataMovie.generateDataMovie()[2]
    private val dummyTvShow = DataMovie.generateDataTvShow()[2]
    private val movieId = dummyMovie.id
    private val tvShowId = dummyTvShow.id

    private val dummyDetailMovie = Resource.success(DataMovie.getDetailMovie())
    private val movieDetail = MutableLiveData<Resource<MovieEntity>>()

    private val dummyDetailTvShow = Resource.success(DataMovie.getDetailTvShow())
    private val tvShowDetail = MutableLiveData<Resource<TvShowEntity>>()

    private val viewModel by inject<DetailMovieViewModel>()

    @Mock
    private lateinit var movieObserver: Observer<Resource<MovieEntity>>

    @Mock
    private lateinit var tvShowObserver: Observer<Resource<TvShowEntity>>


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
    fun getMovieDetail() {
        val movieRepository = declareMock<MovieCatalogueRepository>()
        movieDetail.value = dummyDetailMovie

        viewModel.setSelectedMovie(movieId)
        `when`(movieRepository.getMovieDetail(movieId)).thenReturn(movieDetail)
        viewModel.setDetail(MOVIE)

        val movie = viewModel.getDetailMovie().value
        verify(movieRepository).getMovieDetail(movieId)
        assertNotNull(movie)
        assertEquals(movie, dummyDetailMovie)

        viewModel.getDetailMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyDetailMovie)

    }

    @Test
    fun setFavoriteMovie() {
        val movieRepository = declareMock<MovieCatalogueRepository>()
        movieDetail.value = dummyDetailMovie

        viewModel.setSelectedMovie(movieId)
        `when`(movieRepository.getMovieDetail(movieId)).thenReturn(movieDetail)
        viewModel.setDetail(MOVIE)
        viewModel.setFavoriteMovie()
        verify(movieRepository).setFavoriteMovie(movieDetail.value!!.data as MovieEntity, true)
        verifyNoMoreInteractions(movieObserver)
    }

    @Test
    fun getTvShowDetail() {
        val movieRepository = declareMock<MovieCatalogueRepository>()
        tvShowDetail.value = dummyDetailTvShow

        viewModel.setSelectedTvShow(tvShowId)
        `when`(movieRepository.getTvShowDetail(tvShowId)).thenReturn(tvShowDetail)
        viewModel.setDetail(TV_SHOW)

        val tvShow = viewModel.getDetailTvShow().value
        verify(movieRepository).getTvShowDetail(tvShowId)
        assertNotNull(tvShow)
        assertEquals(tvShow, dummyDetailTvShow)

        viewModel.getDetailTvShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(dummyDetailTvShow)
    }

    @Test
    fun setFavoriteTvShow() {
        val movieRepository = declareMock<MovieCatalogueRepository>()
        tvShowDetail.value = dummyDetailTvShow

        viewModel.setSelectedTvShow(tvShowId)
        `when`(movieRepository.getTvShowDetail(tvShowId)).thenReturn(tvShowDetail)
        viewModel.setDetail(TV_SHOW)
        viewModel.setFavoriteTvShow()

        verify(movieRepository).setFavoriteTvShow(tvShowDetail.value!!.data as TvShowEntity, true)
        verifyNoMoreInteractions(tvShowObserver)
    }

}