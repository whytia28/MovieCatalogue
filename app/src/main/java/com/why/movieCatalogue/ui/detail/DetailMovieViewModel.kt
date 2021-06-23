package com.why.movieCatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.why.movieCatalogue.data.MovieCatalogueRepository
import com.why.movieCatalogue.data.source.local.entity.MovieEntity
import com.why.movieCatalogue.data.source.local.entity.TvShowEntity
import com.why.movieCatalogue.vo.Resource

class DetailMovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    companion object {
        const val MOVIE = "movie"
        const val TV_SHOW = "tvShow"
    }

    private var movieId: Int = 0
    private var tvShowId: Int = 0

    private lateinit var detailTvShow: LiveData<Resource<TvShowEntity>>
    private lateinit var detailMovie: LiveData<Resource<MovieEntity>>

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId = tvShowId
    }

    fun setDetail(category: String) {
        when(category) {
            TV_SHOW -> {
                detailTvShow = movieCatalogueRepository.getTvShowDetail(tvShowId)
            }
            MOVIE -> {
                detailMovie = movieCatalogueRepository.getMovieDetail(movieId)
            }
        }
    }

    fun setFavoriteMovie() {
        val resource = detailMovie.value
        if (resource?.data != null) {
            val newState = !resource.data.isFav
            movieCatalogueRepository.setFavoriteMovie(resource.data, newState)
        }
    }

    fun setFavoriteTvShow() {
        val resource = detailTvShow.value
        if (resource?.data != null) {
            val newState = !resource.data.isFav
            movieCatalogueRepository.setFavoriteTvShow(resource.data, newState)
        }
    }

    fun getDetailMovie() = detailMovie

    fun getDetailTvShow() = detailTvShow


}