package com.why.movieCatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.why.movieCatalogue.BuildConfig.API_KEY
import com.why.movieCatalogue.data.source.remote.response.*
import com.why.movieCatalogue.network.ApiService
import com.why.movieCatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val apiService: ApiService) {


    fun getAllMovie(): LiveData<ApiResponse<List<ResultMovie>>> {
        EspressoIdlingResource.increment()
        val resultMovie = MutableLiveData<ApiResponse<List<ResultMovie>>>()

        apiService.getAllMovie(API_KEY, 1).enqueue(object : Callback<ListMovieResponse> {
            override fun onResponse(
                call: Call<ListMovieResponse>,
                response: Response<ListMovieResponse>
            ) {
                EspressoIdlingResource.decrement()

                resultMovie.value =
                    ApiResponse.success(response.body()?.results as List<ResultMovie>)
            }

            override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
                Log.e("GetAllMovie", "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultMovie
    }

    fun getAllTvShow(): LiveData<ApiResponse<List<ResultTvShow>>> {
        EspressoIdlingResource.increment()
        val resultTvShow = MutableLiveData<ApiResponse<List<ResultTvShow>>>()

        apiService.getAllTvShow(API_KEY, "1").enqueue(object : Callback<ListTvShowResponse> {
            override fun onResponse(
                call: Call<ListTvShowResponse>,
                response: Response<ListTvShowResponse>
            ) {
                EspressoIdlingResource.decrement()

                resultTvShow.value =
                    ApiResponse.success(response.body()?.results as List<ResultTvShow>)
            }

            override fun onFailure(call: Call<ListTvShowResponse>, t: Throwable) {
                Log.e("GetAllTvShow", "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
        return resultTvShow
    }

    fun getMovieDetail(movieId: Int): LiveData<ApiResponse<MovieResponse>> {
        EspressoIdlingResource.increment()
        val movieDetail = MutableLiveData<ApiResponse<MovieResponse>>()

        apiService.getDetailMovie(movieId, API_KEY).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                EspressoIdlingResource.decrement()

                movieDetail.value = ApiResponse.success(response.body() as MovieResponse)
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e("GetMovieDetail", "onFailure: ${t.message.toString()}")
                EspressoIdlingResource.decrement()
            }
        })
        return movieDetail
    }

    fun getTvShowDetail(tvShowId: Int): LiveData<ApiResponse<TvShowResponse>> {
        EspressoIdlingResource.increment()
        val tvShowDetail = MutableLiveData<ApiResponse<TvShowResponse>>()

        apiService.getTvShowDetail(tvShowId, API_KEY).enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                EspressoIdlingResource.decrement()

                tvShowDetail.value = ApiResponse.success(response.body() as TvShowResponse)
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e("GetTvShowDetail", "onFailure: ${t.message.toString()}")
            }
        })
        return tvShowDetail
    }

}