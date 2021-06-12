package com.why.movieCatalogue.network

import com.why.movieCatalogue.data.source.remote.response.ListMovieResponse
import com.why.movieCatalogue.data.source.remote.response.ListTvShowResponse
import com.why.movieCatalogue.data.source.remote.response.MovieResponse
import com.why.movieCatalogue.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.http.*


interface ApiService {


    @GET("discover/movie")
    fun getAllMovie(
        @Query("api_key") auth: String,
        @Query("page") page: Int
    ): Call<ListMovieResponse>

    @GET("discover/tv")
    fun getAllTvShow(
        @Query("api_key") auth: String,
        @Query("page") page: String
    ): Call<ListTvShowResponse>

    @GET("movie/{movie_id}")
    fun getDetailMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") auth: String
    ): Call<MovieResponse>

    @GET("tv/{tv_id}")
    fun getTvShowDetail(
        @Path("tv_id") id: Int,
        @Query("api_key") auth: String
    ): Call<TvShowResponse>

}