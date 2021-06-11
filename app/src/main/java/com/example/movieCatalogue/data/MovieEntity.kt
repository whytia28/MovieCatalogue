package com.example.movieCatalogue.data

data class MovieEntity(
    var movieId: Int,
    var title: String?,
    var synopsis: String?,
    var releaseDate: String?,
    var category: List<String>,
    var imagePoster: String?
)