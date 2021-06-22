package com.why.movieCatalogue.ui.movie

import com.why.movieCatalogue.data.source.local.entity.MovieEntity

interface MovieCallback {
    fun onShareClick(movie: MovieEntity)

}
