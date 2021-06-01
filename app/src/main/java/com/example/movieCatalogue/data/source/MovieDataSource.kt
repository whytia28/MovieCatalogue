package com.example.movieCatalogue.data.source

import androidx.lifecycle.LiveData
import com.example.movieCatalogue.data.MovieEntity
import com.example.movieCatalogue.data.TvShowEntity

interface MovieDataSource {
    fun getAllMovie(): LiveData<List<MovieEntity>>

    fun getAllTvShow(): LiveData<List<TvShowEntity>>

    fun getMovieDetail(movieId: Int): LiveData<MovieEntity>

    fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity>
}