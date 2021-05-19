package com.example.movieCatalogue.ui.movie

import com.example.movieCatalogue.data.MovieEntity

interface MovieCallback {
    fun onShareClick(movie: MovieEntity)

}
