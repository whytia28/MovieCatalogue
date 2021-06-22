package com.why.movieCatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.why.movieCatalogue.data.MovieDataSource
import com.why.movieCatalogue.data.source.local.entity.MovieEntity
import com.why.movieCatalogue.data.source.local.entity.TvShowEntity
import com.why.movieCatalogue.data.source.remote.RemoteDataSource
import com.why.movieCatalogue.data.source.remote.response.MovieResponse
import com.why.movieCatalogue.data.source.remote.response.ResultMovie
import com.why.movieCatalogue.data.source.remote.response.ResultTvShow
import com.why.movieCatalogue.data.source.remote.response.TvShowResponse

class FakeMovieCatalogueRepository(private val remoteDataSource: RemoteDataSource) :
    MovieDataSource {

    override fun getAllMovie(): LiveData<List<MovieEntity>> {
        val movieResult = MutableLiveData<List<MovieEntity>>()

        remoteDataSource.getAllMovie(object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMovieReceived(movieResponse: List<ResultMovie>) {
                val movieList = ArrayList<MovieEntity>()
                movieResponse.let { resultMovie ->
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
                movieResult.postValue(movieList)
            }

        })
        return movieResult
    }

    override fun getAllTvShow(): LiveData<List<TvShowEntity>> {
        val tvShowResult = MutableLiveData<List<TvShowEntity>>()

        remoteDataSource.getAllTvShow(object : RemoteDataSource.LoadTvShowCallback {
            override fun onAllTvShowReceived(tvShowResponse: List<ResultTvShow>) {
                val tvShowList = ArrayList<TvShowEntity>()
                tvShowResponse.let { resultTvShow ->
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
                tvShowResult.postValue(tvShowList)
            }

        })
        return tvShowResult
    }

    override fun getMovieDetail(movieId: Int): LiveData<MovieEntity> {
        val movieDetail = MutableLiveData<MovieEntity>()

        remoteDataSource.getMovieDetail(movieId, object : RemoteDataSource.MovieDetailCallback {
            override fun onMovieDetailReceived(movieDetailResponse: MovieResponse) {

                movieDetailResponse.let { movieResponse ->
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

        })
        return movieDetail
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<TvShowEntity> {
        val tvShowDetail = MutableLiveData<TvShowEntity>()

        remoteDataSource.getTvShowDetail(tvShowId, object : RemoteDataSource.TvShowDetailCallback {
            override fun onTvShowDetailReceived(tvSHowDetailResponse: TvShowResponse) {

                tvSHowDetailResponse.let { tvShowResponse ->
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

        })
        return tvShowDetail
    }
}