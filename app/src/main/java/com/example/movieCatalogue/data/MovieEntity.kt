package com.example.movieCatalogue.data

import android.graphics.drawable.Drawable

data class MovieEntity (
    var movieId: String,
    var title: String,
    var synopsis: String,
    var releaseDate: String,
    var category: String,
    var imagePoster: Int
        )