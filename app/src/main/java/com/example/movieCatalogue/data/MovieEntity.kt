package com.example.movieCatalogue.data

data class MovieEntity (
    var movieId: String,
    var title: String,
    var synopsis: String,
    var releaseDate: String,
    var category: String,
    var imagePoster: Int
        )