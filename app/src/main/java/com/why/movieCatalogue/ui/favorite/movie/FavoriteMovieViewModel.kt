package com.why.movieCatalogue.ui.favorite.movie

import androidx.lifecycle.ViewModel
import com.why.movieCatalogue.data.MovieCatalogueRepository
import com.why.movieCatalogue.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {
    fun getFavMovie() = movieCatalogueRepository.getFavoriteMovie()

    fun setFavMovie(movie: MovieEntity) {
        val newState = !movie.isFav
        movieCatalogueRepository.setFavoriteMovie(movie, newState)
    }
}