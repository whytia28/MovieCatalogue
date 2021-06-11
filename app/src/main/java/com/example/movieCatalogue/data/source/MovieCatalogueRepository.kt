package com.example.movieCatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieCatalogue.data.MovieEntity
import com.example.movieCatalogue.data.TvShowEntity
import com.example.movieCatalogue.data.source.remote.RemoteDataSource
import com.example.movieCatalogue.data.source.remote.response.ListMovieResponse
import com.example.movieCatalogue.data.source.remote.response.ListTvShowResponse
import com.example.movieCatalogue.data.source.remote.response.MovieResponse
import com.example.movieCatalogue.data.source.remote.response.TvShowResponse
import retrofit2.Response

class MovieCatalogueRepository(private val remoteDataSource: RemoteDataSource) : MovieDataSource {


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    override fun getAllMovie(): LiveData<List<MovieEntity>> {
        _isLoading.value = true
        val movieResult = MutableLiveData<List<MovieEntity>>()

        remoteDataSource.getAllMovie(object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMovieReceived(movieResponse: Response<ListMovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                _isLoading.value = false
                movieResponse.body()?.results.let { resultMovie ->
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


        })
        return movieResult
    }

    override fun getAllTvShow(): LiveData<List<TvShowEntity>> {
        val tvShowResult = MutableLiveData<List<TvShowEntity>>()
        _isLoading.value = true

        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowResponse: Response<ListTvShowResponse>) {
                _isLoading.value = false
                val tvShowList = ArrayList<TvShowEntity>()
                tvShowResponse.body()?.results.let { resultTvShow ->
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

        })

        return tvShowResult
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovieEntity> {
        val movieDetail = MutableLiveData<MovieEntity>()
        _isLoading.value = true

        remoteDataSource.getMovieDetail(movieId, object : RemoteDataSource.MovieDetailCallback {
            override fun onMovieDetailReceived(movieDetailResponse: Response<MovieResponse>) {
                _isLoading.value = false

                movieDetailResponse.body().let { movieResponse ->
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

        })
        return movieDetail
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity> {
        val tvShowDetail = MutableLiveData<TvShowEntity>()
        _isLoading.value = true

        remoteDataSource.getTvShowDetail(tvShowId, object : RemoteDataSource.TvShowDetailCallback {
            override fun onTvShowDetailReceived(tvSHowDetailResponse: Response<TvShowResponse>) {
                _isLoading.value = false

                tvSHowDetailResponse.body().let { tvShowResponse ->
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

        })
        return tvShowDetail
    }
}