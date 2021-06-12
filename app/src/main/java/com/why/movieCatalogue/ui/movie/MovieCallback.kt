package com.why.movieCatalogue.ui.movie

import com.why.movieCatalogue.data.MovieEntity

interface MovieCallback {
    fun onShareClick(movie: MovieEntity)

}
