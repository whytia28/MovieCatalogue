package com.example.movieCatalogue.data.source.remote

import com.example.movieCatalogue.data.source.remote.response.*
import com.example.movieCatalogue.network.ApiService

class RemoteDataSource(private val apiService: ApiService) {

    @Volatile
    private var instance: RemoteDataSource? = null

    fun getInstance(apiService: ApiService): RemoteDataSource = instance ?: synchronized(this) {
        instance ?: RemoteDataSource(apiService).apply { instance = this }
    }

//    fun getAllMovie(callBack: LoadMovieCallback) {
//        callBack.onAllMoviesReceived(apiService.getAllMovie(API_KEY))
//    }






    interface LoadMovieCallback {
        fun onAllMoviesReceived(movieResponse: List<ResultMovie>)
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShowResponse: List<ResultMovie>)
    }
    interface LoadDetailMovieCallback {
        fun onDetailMovieReceived(detailMovieResponse: MovieResponse)
    }
    interface LoadDetailTvShowCallback {
        fun onDetailTvShowReceived(detailTvShowResponse: TvShowResponse)
    }
}