package com.example.movieCatalogue.ui.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieCatalogue.data.MovieEntity
import com.example.movieCatalogue.data.source.MovieRepository

class MovieViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getMovies(): LiveData<List<MovieEntity>> = movieRepository.getAllMovie()
    fun getLoading(): LiveData<Boolean> = movieRepository.isLoading

}