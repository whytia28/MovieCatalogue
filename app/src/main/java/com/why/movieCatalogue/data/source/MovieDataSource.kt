package com.why.movieCatalogue.data.source

import androidx.lifecycle.LiveData
import com.why.movieCatalogue.data.MovieEntity
import com.why.movieCatalogue.data.TvShowEntity

interface MovieDataSource {
    fun getAllMovie(): LiveData<List<MovieEntity>>

    fun getAllTvShow(): LiveData<List<TvShowEntity>>

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity>

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity>
}