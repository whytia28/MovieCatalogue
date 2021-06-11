package com.example.movieCatalogue.data.source.remote

import com.example.movieCatalogue.data.source.remote.response.ListMovieResponse
import com.example.movieCatalogue.data.source.remote.response.ListTvShowResponse
import com.example.movieCatalogue.data.source.remote.response.MovieResponse
import com.example.movieCatalogue.data.source.remote.response.TvShowResponse
import com.example.movieCatalogue.network.ApiService
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {
    companion object {

        private const val API_KEY = "68ff07774df041eef8a191fa74aa7bb2"
    }

    fun getAllMovie(callBack: LoadMovieCallback) {
        callBack.onAllMovieReceived(apiService.getAllMovie(API_KEY,"1").execute())
    }

    fun getAllTvShow(callback: LoadTvShowCallback) {
        callback.onAllTvShowReceived(apiService.getAllTvShow(API_KEY, "1").execute())
    }

    fun getMovieDetail(movieId: Int, callBack: MovieDetailCallback) {
        callBack.onMovieDetailReceived(apiService.getDetailMovie(movieId, API_KEY).execute())
    }

    fun getTvShowDetail(tvShowId: Int, callBack: TvShowDetailCallback) {
        callBack.onTvShowDetailReceived(apiService.getTvShowDetail(tvShowId, API_KEY).execute())
    }

    interface TvShowDetailCallback {
        fun onTvShowDetailReceived(tvSHowDetailResponse: Response<TvShowResponse>)
    }

    interface MovieDetailCallback {
        fun onMovieDetailReceived(movieDetailResponse: Response<MovieResponse>)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShowResponse: Response<ListTvShowResponse>)
    }

    interface LoadMovieCallback {
        fun onAllMovieReceived(movieResponse: Response<ListMovieResponse>)
    }
}