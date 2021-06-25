package com.why.movieCatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.why.movieCatalogue.data.source.local.LocalDataSource
import com.why.movieCatalogue.data.source.local.entity.MovieEntity
import com.why.movieCatalogue.data.source.local.entity.TvShowEntity
import com.why.movieCatalogue.data.source.remote.RemoteDataSource
import com.why.movieCatalogue.utils.AppExecutors
import com.why.movieCatalogue.utils.DataMovie
import com.why.movieCatalogue.utils.LiveDataTestUtil
import com.why.movieCatalogue.utils.PagedListUtils
import com.why.movieCatalogue.vo.Resource
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class MovieCatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val localDataSource = mock(LocalDataSource::class.java)
    private val remoteData = mock(RemoteDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val movieRepository =
        FakeMovieCatalogueRepository(remoteData, localDataSource, appExecutors)

    private val movieResponse = DataMovie.getRemoteMovies()
    private val movieDetail = DataMovie.getRemoteDetailMovie()
    private val movieId = movieResponse[0].id
    private val tvShowResponse = DataMovie.getRemoteTvShows()
    private val tvShowDetail = DataMovie.getRemoteDetailTvShow()
    private val tvShowId = tvShowResponse[2].id


    @Test
    fun getAllMovie() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localDataSource.getAllMovies("ASCEND")).thenReturn(dataSourceFactory)
        movieRepository.getAllMovie("ASCEND")
        val movie = Resource.success(PagedListUtils.mockPagedList(DataMovie.generateDataMovie()))
        verify(localDataSource).getAllMovies("ASCEND")
        assertNotNull(movie)
        assertEquals(movieResponse.size, movie.data?.size)
    }

    @Test
    fun getAllTvShow() {
        val dataSourceFactory =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(localDataSource.getAllTvShows("ASCEND")).thenReturn(dataSourceFactory)
        movieRepository.getAllTvShow("ASCEND")
        val tvShow = Resource.success(PagedListUtils.mockPagedList(DataMovie.generateDataTvShow()))
        verify(localDataSource).getAllTvShows("ASCEND")

        assertNotNull(tvShow)
        assertEquals(tvShowResponse.size, tvShow.data?.size)
    }

    @Test
    fun getMovieDetail() {
        val dummyDetail = MutableLiveData<MovieEntity>()
        dummyDetail.value = DataMovie.getDetailMovie()
        `when`(localDataSource.getMovieDetail(movieId)).thenReturn(dummyDetail)

        val movieDetailEntity = LiveDataTestUtil.getValue(movieRepository.getMovieDetail(movieId))
        verify(localDataSource).getMovieDetail(movieId)
        assertNotNull(movieDetailEntity)
        assertEquals(movieDetail.id, movieDetailEntity.data?.id)
    }

    @Test
    fun getTvShowDetail() {
        val dummyDetail = MutableLiveData<TvShowEntity>()
        dummyDetail.value = DataMovie.getDetailTvShow()
        `when`(localDataSource.getTvShowDetail(tvShowId)).thenReturn(dummyDetail)

        val tvShowDetails = LiveDataTestUtil.getValue(movieRepository.getTvShowDetail(tvShowId))
        verify(localDataSource).getTvShowDetail(tvShowId)

        assertNotNull(tvShowDetails)
        assertEquals(tvShowDetail.id, tvShowDetails.data?.id)
        assertEquals(tvShowDetail.name, tvShowDetails.data?.title)
    }

    @Test
    fun getFavoriteMovie() {
        val dataSource =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(localDataSource.getFavMovies()).thenReturn(dataSource)
        movieRepository.getFavoriteMovie()

        val movie = Resource.success(PagedListUtils.mockPagedList(DataMovie.generateDataMovie()))
        verify(localDataSource).getFavMovies()
        assertNotNull(movie)
        assertEquals(movieResponse.size, movie.data?.size)
    }

    @Test
    fun getFavoriteTvShow() {
        val datSource =
            mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(localDataSource.getFavTvShows()).thenReturn(datSource)
        movieRepository.getFavoriteTvShow()

        val tvShow = Resource.success(PagedListUtils.mockPagedList(DataMovie.generateDataTvShow()))
        verify(localDataSource).getFavTvShows()
        assertNotNull(tvShow)
        assertEquals(tvShowResponse.size, tvShow.data?.size)
    }

    @Test
    fun setFavMovie() {
        movieRepository.setFavoriteMovie(DataMovie.getDetailMovie(), true)
        verify(localDataSource).setFavMovie(DataMovie.getDetailMovie(), true)
        verifyNoMoreInteractions(localDataSource)
    }

    @Test
    fun setFavTvShow() {
        movieRepository.setFavoriteTvShow(DataMovie.getDetailTvShow(), true)
        verify(localDataSource).setFavTvShow(DataMovie.getDetailTvShow(), true)
        verifyNoMoreInteractions(localDataSource)
    }

}