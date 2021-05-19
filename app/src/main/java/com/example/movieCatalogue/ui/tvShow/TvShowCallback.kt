package com.example.movieCatalogue.ui.tvShow

import com.example.movieCatalogue.data.TvShowEntity

interface TvShowCallback {
    fun onShareClick(tvShow: TvShowEntity)
}
