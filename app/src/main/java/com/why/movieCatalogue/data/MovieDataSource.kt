package com.why.movieCatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.why.movieCatalogue.data.source.local.entity.MovieEntity
import com.why.movieCatalogue.data.source.local.entity.TvShowEntity
import com.why.movieCatalogue.vo.Resource

interface MovieDataSource {
    fun getAllMovie(): LiveData<Resource<PagedList<MovieEntity>>>

    fun getAllTvShow(): LiveData<Resource<PagedList<TvShowEntity>>>

    fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>>

    fun getTvShowDetail(tvShowId: Int): LiveData<Resource<TvShowEntity>>

    fun setFavoriteMovie(movie: MovieEntity, state: Boolean)

    fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean)

    fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>>

    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>
}