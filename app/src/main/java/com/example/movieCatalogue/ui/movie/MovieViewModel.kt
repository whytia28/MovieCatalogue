package com.example.movieCatalogue.ui.movie

import androidx.lifecycle.ViewModel
import com.example.movieCatalogue.data.MovieEntity
import com.example.movieCatalogue.utils.DataMovie

class MovieViewModel : ViewModel() {
    fun getMovies(): List<MovieEntity> = DataMovie.generateDataMovie()
}