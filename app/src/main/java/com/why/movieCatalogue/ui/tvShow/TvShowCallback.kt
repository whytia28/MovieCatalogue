package com.why.movieCatalogue.ui.tvShow

import com.why.movieCatalogue.data.TvShowEntity

interface TvShowCallback {
    fun onShareClick(tvShow: TvShowEntity)
}
