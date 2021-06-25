package com.why.movieCatalogue.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ListMovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<ResultMovie>
)

data class ResultMovie(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("genre_ids")
    val genreIds: List<String>,
    @SerializedName("poster_path")
    val posterPath: String,


)
