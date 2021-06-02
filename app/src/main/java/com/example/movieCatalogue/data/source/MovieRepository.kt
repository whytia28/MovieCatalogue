package com.example.movieCatalogue.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieCatalogue.data.MovieEntity
import com.example.movieCatalogue.data.TvShowEntity
import com.example.movieCatalogue.data.source.remote.response.*
import com.example.movieCatalogue.network.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(private val apiService: ApiService) : MovieDataSource {

    companion object {

        private const val API_KEY = "68ff07774df041eef8a191fa74aa7bb2"
    }

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    override fun getAllMovie(): LiveData<List<MovieEntity>> {
        _isLoading.value = true
        val movieResult = MutableLiveData<List<MovieEntity>>()

        apiService.getAllMovie(API_KEY, "1").enqueue(object : Callback<ListMovieResponse> {
            override fun onResponse(
                call: Call<ListMovieResponse>,
                response: Response<ListMovieResponse>
            ) {
                _isLoading.value = false
                val movieList = ArrayList<MovieEntity>()
                response.body()?.results.let { resultMovie ->
                    if (resultMovie != null) {
                        for (result in resultMovie) {
                            val movie = MovieEntity(
                                result.id,
                                result.title,
                                result.overview,
                                result.releaseDate,
                                result.genreIds,
                                result.posterPath
                            )
                            movieList.add(movie)
                        }
                    }

                }
                movieResult.postValue(movieList)
            }

            override fun onFailure(call: Call<ListMovieResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("GetAllMovie", "onFailure: ${t.message.toString()}")
            }


        })
        return movieResult
    }

    override fun getAllTvShow(): LiveData<List<TvShowEntity>> {
        _isLoading.value = true
        val tvShowResult = MutableLiveData<List<TvShowEntity>>()

        apiService.getAllTvShow(API_KEY, "1").enqueue(object : Callback<ListTvShowResponse> {
            override fun onResponse(
                call: Call<ListTvShowResponse>,
                response: Response<ListTvShowResponse>
            ) {
                _isLoading.value = false
                val tvShowList = ArrayList<TvShowEntity>()
                response.body()?.results.let { resultTvShow ->
                    if (resultTvShow != null) {
                        for (result in resultTvShow) {
                            val tvShow = TvShowEntity(
                                result.id,
                                result.originalName,
                                result.overview,
                                result.firstAirDate,
                                result.genreIds,
                                result.posterPath
                            )
                            tvShowList.add(tvShow)
                        }

                    }
                }
                tvShowResult.postValue(tvShowList)
            }

            override fun onFailure(call: Call<ListTvShowResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("GetAllTvShow", "onFailure: ${t.message.toString()}")
            }
        })
        return tvShowResult
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovieEntity> {
        _isLoading.value = true
        val movieDetail = MutableLiveData<MovieEntity>()

        apiService.getDetailMovie(movieId, API_KEY).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                _isLoading.value = false
                response.body().let { movieResponse ->
                    if (movieResponse != null) {
                        if (movieResponse.id == movieId) {
                            val movie = MovieEntity(
                                movieResponse.id,
                                movieResponse.title,
                                movieResponse.overview,
                                movieResponse.releaseDate,
                                movieResponse.genres.map { it.name },
                                movieResponse.posterPath

                            )
                            movieDetail.postValue(movie)
                        }
                    }

                }

            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("GetDetailMovie", "onFailure: ${t.message.toString()}")
            }

        })
        return movieDetail

    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity> {
        _isLoading.value = true
        val tvShowDetail = MutableLiveData<TvShowEntity>()

        apiService.getTvShowDetail(tvShowId, API_KEY).enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(
                call: Call<TvShowResponse>,
                response: Response<TvShowResponse>
            ) {
                _isLoading.value = false
                response.body().let { tvShowResponse ->
                    if (tvShowResponse != null) {
                        val tvShow = TvShowEntity(
                            tvShowResponse.id,
                            tvShowResponse.name,
                            tvShowResponse.overview,
                            tvShowResponse.firstAirDate,
                            tvShowResponse.genres.map { it.name },
                            tvShowResponse.posterPath
                        )
                        tvShowDetail.postValue(tvShow)
                    }
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e("GetDetailTvShow", "onFailure: ${t.message.toString()}")
            }

        })
        return tvShowDetail
    }
}