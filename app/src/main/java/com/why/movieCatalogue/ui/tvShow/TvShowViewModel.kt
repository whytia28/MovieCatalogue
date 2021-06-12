package com.why.movieCatalogue.ui.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.why.movieCatalogue.data.TvShowEntity
import com.why.movieCatalogue.data.source.MovieCatalogueRepository

class TvShowViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    fun getTvShows(): LiveData<List<TvShowEntity>> = movieCatalogueRepository.getAllTvShow()
    fun getLoading(): LiveData<Boolean> = movieCatalogueRepository.isLoading
}