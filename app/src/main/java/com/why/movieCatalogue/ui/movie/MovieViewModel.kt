package com.why.movieCatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.why.movieCatalogue.data.MovieCatalogueRepository

class MovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    fun getMovies() = movieCatalogueRepository.getAllMovie()
}