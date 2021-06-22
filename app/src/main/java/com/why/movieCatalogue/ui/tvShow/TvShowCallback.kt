package com.why.movieCatalogue.ui.tvShow

import com.why.movieCatalogue.data.source.local.entity.TvShowEntity

interface TvShowCallback {
    fun onShareClick(tvShow: TvShowEntity)
}
