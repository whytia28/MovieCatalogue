package com.why.movieCatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.why.movieCatalogue.BuildConfig.API_KEY
import com.why.movieCatalogue.data.source.remote.response.*
import com.why.movieCatalogue.network.ApiService
import com.why.movieCatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {


    val isLoading = MutableLiveData<Boolean>()


    fun getAllMovie(callBack: LoadMovieCallback) {
        EspressoIdlingResource.increment()
        isLoading.value = true

        apiService.getAllMovie(API_KEY, 1).enqueue(object : Callback<ListMovieResponse> {
            override fun onResponse(
                call: Call<ListMovieResponse>,
                response: Response<ListMovieResponse>
            ) {
                isLoading.value = false
                EspressoIdlingResource.decrement()

                response.body()?.results?.let { callBack.onAllMovieReceived(it) }
            }

            override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
                Log.e("GetAllMovie", "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getAllTvShow(callback: LoadTvShowCallback) {
        EspressoIdlingResource.increment()
        isLoading.value = true

        apiService.getAllTvShow(API_KEY, "1").enqueue(object : Callback<ListTvShowResponse> {
            override fun onResponse(
                call: Call<ListTvShowResponse>,
                response: Response<ListTvShowResponse>
            ) {
                isLoading.value = false
                EspressoIdlingResource.decrement()

                response.body()?.results?.let { callback.onAllTvShowReceived(it) }
            }

            override fun onFailure(call: Call<ListTvShowResponse>, t: Throwable) {
                Log.e("GetAllTvShow", "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getMovieDetail(movieId: Int, callBack: MovieDetailCallback) {
        EspressoIdlingResource.increment()
        isLoading.value = true

        apiService.getDetailMovie(movieId, API_KEY).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                EspressoIdlingResource.decrement()
                isLoading.value = false

                response.body()?.let { callBack.onMovieDetailReceived(it) }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("GetMovieDetail", "onFailure: ${t.message.toString()}")
            }
        })
    }

    fun getTvShowDetail(tvShowId: Int, callBack: TvShowDetailCallback) {
        EspressoIdlingResource.increment()
        isLoading.value = true

        apiService.getTvShowDetail(tvShowId, API_KEY).enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                EspressoIdlingResource.decrement()
                isLoading.value = false

                response.body()?.let { callBack.onTvShowDetailReceived(it) }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e("GetTvShowDetail", "onFailure: ${t.message.toString()}")
            }
        })
    }

    interface TvShowDetailCallback {
        fun onTvShowDetailReceived(tvSHowDetailResponse: TvShowResponse)
    }

    interface MovieDetailCallback {
        fun onMovieDetailReceived(movieDetailResponse: MovieResponse)
    }

    interface LoadTvShowCallback {
        fun onAllTvShowReceived(tvShowResponse: List<ResultTvShow>)
    }

    interface LoadMovieCallback {
        fun onAllMovieReceived(movieResponse: List<ResultMovie>)
    }
}