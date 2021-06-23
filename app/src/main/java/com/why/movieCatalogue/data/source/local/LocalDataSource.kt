package com.why.movieCatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.why.movieCatalogue.data.source.local.entity.MovieEntity
import com.why.movieCatalogue.data.source.local.entity.TvShowEntity
import com.why.movieCatalogue.data.source.local.room.MovieCatalogueDao

class LocalDataSource(private val mMovieCatalogueDao: MovieCatalogueDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieCatalogueDao: MovieCatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieCatalogueDao)
    }

    fun getAllMovies(): DataSource.Factory<Int, MovieEntity> = mMovieCatalogueDao.getAllMovies()

    fun getAllTvShows(): DataSource.Factory<Int, TvShowEntity> = mMovieCatalogueDao.getAllTvShow()

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity> =
        mMovieCatalogueDao.getMovieById(movieId)

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity> =
        mMovieCatalogueDao.getTvSHowById(tvShowId)

    fun insertMovie(movie: List<MovieEntity>) = mMovieCatalogueDao.insertMovie(movie)

    fun insertTvShow(tvShow: List<TvShowEntity>) = mMovieCatalogueDao.insertTvShow(tvShow)

    fun getFavTvShows(): DataSource.Factory<Int, TvShowEntity> = mMovieCatalogueDao.getFavTvShows()

    fun getFavMovies(): DataSource.Factory<Int, MovieEntity> = mMovieCatalogueDao.getFavMovies()

    fun setFavMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFav = newState
        mMovieCatalogueDao.updateMovie(movie)
    }

    fun setFavTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFav = newState
        mMovieCatalogueDao.updateTvShow(tvShow)
    }

    fun updateTvShow(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.isFav = newState
        mMovieCatalogueDao.updateTvShow(tvShow)
    }

    fun updateMovie(movie: MovieEntity, newState: Boolean) {
        movie.isFav = newState
        mMovieCatalogueDao.updateMovie(movie)
    }


}