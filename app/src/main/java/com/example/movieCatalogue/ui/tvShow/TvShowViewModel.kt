package com.example.movieCatalogue.ui.tvShow

import androidx.lifecycle.ViewModel
import com.example.movieCatalogue.data.TvShowEntity
import com.example.movieCatalogue.utils.DataMovie

class TvShowViewModel : ViewModel() {
    fun getTvShows() : List<TvShowEntity> = DataMovie.generateDataTvShow()
}