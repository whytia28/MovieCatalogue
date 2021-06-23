package com.why.movieCatalogue.ui.tvShow

import androidx.lifecycle.ViewModel
import com.why.movieCatalogue.data.MovieCatalogueRepository

class TvShowViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {
    fun getTvShows() = movieCatalogueRepository.getAllTvShow()
}