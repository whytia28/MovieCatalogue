package com.example.movieCatalogue.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class ListTvShowResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<ResultTvShow>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)

data class ResultTvShow(
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("first_air_date")
    val firstAirDate: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("original_language")
    val originalLanguage: String,
    @SerializedName("original_name")
    val originalName: String,
    @SerializedName("genre_ids")
    val genreIds: List<String>,
    @SerializedName("overview")
    val overview: String,
)