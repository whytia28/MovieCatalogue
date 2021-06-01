package com.example.movieCatalogue.ui.tvShow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.movieCatalogue.data.TvShowEntity
import com.example.movieCatalogue.data.source.MovieRepository

class TvShowViewModel(private val movieRepository: MovieRepository) : ViewModel() {
    fun getTvShows(): LiveData<List<TvShowEntity>> = movieRepository.getAllTvShow()
    fun getLoading(): LiveData<Boolean> = movieRepository.isLoading
}