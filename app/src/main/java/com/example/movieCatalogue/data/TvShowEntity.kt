package com.example.movieCatalogue.data

data class TvShowEntity(
    var tvShowId: Int?,
    var title: String?,
    var synopsis: String?,
    var releaseDate: String?,
    var category: List<String>,
    var imagePoster: String?
)