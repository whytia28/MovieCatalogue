package com.why.movieCatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.why.movieCatalogue.data.MovieEntity
import com.why.movieCatalogue.data.source.MovieCatalogueRepository

class MovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    fun getMovies(): LiveData<List<MovieEntity>> = movieCatalogueRepository.getAllMovie()
    fun getLoading(): LiveData<Boolean> = movieCatalogueRepository.isLoading

}