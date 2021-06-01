package com.example.movieCatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieCatalogue.data.MovieEntity
import com.example.movieCatalogue.data.TvShowEntity
import com.example.movieCatalogue.data.source.MovieRepository

class DetailMovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    private var movieId: Int = 0
    private var tvShowId: Int = 0

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId = tvShowId
    }

    fun getMovie(): LiveData<MovieEntity> = movieRepository.getMovieDetail(movieId)

    fun getTvShow(): LiveData<TvShowEntity> = movieRepository.getTvShowDetail(tvShowId)

    fun getLoading(): LiveData<Boolean> = movieRepository.isLoading


}