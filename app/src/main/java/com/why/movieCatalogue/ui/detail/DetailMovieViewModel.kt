package com.why.movieCatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.why.movieCatalogue.data.MovieEntity
import com.why.movieCatalogue.data.TvShowEntity
import com.why.movieCatalogue.data.source.MovieCatalogueRepository

class DetailMovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    private var movieId: Int = 0
    private var tvShowId: Int = 0

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId = tvShowId
    }

    fun getMovie(): LiveData<MovieEntity> = movieCatalogueRepository.getMovieDetail(movieId)

    fun getTvShow(): LiveData<TvShowEntity> = movieCatalogueRepository.getTvShowDetail(tvShowId)

    fun getLoading(): LiveData<Boolean> = movieCatalogueRepository.isLoading


}