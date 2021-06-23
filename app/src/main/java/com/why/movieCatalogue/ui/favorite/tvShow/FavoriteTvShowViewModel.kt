package com.why.movieCatalogue.ui.favorite.tvShow

import androidx.lifecycle.ViewModel
import com.why.movieCatalogue.data.MovieCatalogueRepository
import com.why.movieCatalogue.data.source.local.entity.TvShowEntity

class FavoriteTvShowViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {
    fun getFavTvShow() = movieCatalogueRepository.getFavoriteTvShow()

    fun setFavTvShow(tvShow: TvShowEntity) {
        val newState = !tvShow.isFav
        movieCatalogueRepository.setFavoriteTvShow(tvShow, newState)
    }
}