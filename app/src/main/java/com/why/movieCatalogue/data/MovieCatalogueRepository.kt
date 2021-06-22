package com.why.movieCatalogue.data

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.why.movieCatalogue.data.source.local.LocalDataSource
import com.why.movieCatalogue.data.source.local.entity.MovieEntity
import com.why.movieCatalogue.data.source.local.entity.TvShowEntity
import com.why.movieCatalogue.data.source.local.room.MovieCatalogueDatabase
import com.why.movieCatalogue.data.source.remote.ApiResponse
import com.why.movieCatalogue.data.source.remote.RemoteDataSource
import com.why.movieCatalogue.data.source.remote.response.MovieResponse
import com.why.movieCatalogue.data.source.remote.response.ResultMovie
import com.why.movieCatalogue.data.source.remote.response.ResultTvShow
import com.why.movieCatalogue.data.source.remote.response.TvShowResponse
import com.why.movieCatalogue.utils.AppExecutors
import com.why.movieCatalogue.vo.Resource

class MovieCatalogueRepository(private val remoteDataSource: RemoteDataSource, context: Context) : MovieDataSource {


    private val database = MovieCatalogueDatabase.getInstance(context)
    private val localDataSource = LocalDataSource(database.movieCatalogueDao())
    private val appExecutors = AppExecutors()

    override fun getAllMovie(): LiveData<Resource<PagedList<MovieEntity>>> {

        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<ResultMovie>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultMovie>>> =
                remoteDataSource.getAllMovie()

            override fun saveCallResult(data: List<ResultMovie>) {
                val movieList = ArrayList<MovieEntity>()
                for (result in data) {
                    val movie = MovieEntity(
                        result.id,
                        result.title,
                        result.overview,
                        result.releaseDate,
                        "",
                        result.posterPath,
                        false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovie(movieList)
            }

        }.asLiveData()

    }

    override fun getAllTvShow(): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TvShowEntity>, List<ResultTvShow>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<ResultTvShow>>> =
                remoteDataSource.getAllTvShow()

            override fun saveCallResult(data: List<ResultTvShow>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (result in data) {
                    val tvShow = TvShowEntity(
                        result.id,
                        result.originalName,
                        result.overview,
                        result.firstAirDate,
                        "",
                        result.posterPath,
                        false
                    )
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShow(tvShowList)
            }

        }.asLiveData()
    }

    override fun getMovieDetail(movieId: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> =
                localDataSource.getMovieDetail(movieId)

            override fun shouldFetch(data: MovieEntity?): Boolean =
                data == null || data.imagePoster.isNullOrEmpty()


            override fun createCall(): LiveData<ApiResponse<MovieResponse>> =
                remoteDataSource.getMovieDetail(movieId)

            override fun saveCallResult(data: MovieResponse) {
                val genres = StringBuilder().append("")

                for (i in data.genres.indices) {
                    if (i < data.genres.size - 1) {
                        genres.append("${data.genres[i].name}, ")
                    } else {
                        genres.append(data.genres[i].name)
                    }
                }

                val movie = MovieEntity(
                    data.id,
                    data.title,
                    data.overview,
                    data.releaseDate,
                    data.genres.toString(),
                    data.posterPath,
                    false

                )
                localDataSource.updateMovie(movie, false)
            }

        }.asLiveData()
    }

    override fun getTvShowDetail(tvShowId: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> =
                localDataSource.getTvShowDetail(tvShowId)

            override fun shouldFetch(data: TvShowEntity?): Boolean =
                data == null || data.imagePoster.isNullOrEmpty()

            override fun createCall(): LiveData<ApiResponse<TvShowResponse>> =
                remoteDataSource.getTvShowDetail(tvShowId)

            override fun saveCallResult(data: TvShowResponse) {
                val genres = StringBuilder().append("")

                for (i in data.genres.indices) {
                    if (i < data.genres.size - 1) {
                        genres.append("${data.genres[i].name}, ")
                    } else {
                        genres.append(data.genres[i].name)
                    }
                }
                val tvShow = TvShowEntity(
                    data.id,
                    data.name,
                    data.overview,
                    data.firstAirDate,
                    data.genres.toString(),
                    data.posterPath,
                    false
                )
                localDataSource.updateTvShow(tvShow, false)
            }

        }.asLiveData()

    }

    override fun setFavoriteMovie(movie: MovieEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavMovie(movie, state)
        }
    }

    override fun setFavoriteTvShow(tvShow: TvShowEntity, state: Boolean) {
        appExecutors.diskIO().execute {
            localDataSource.setFavTvShow(tvShow, state)
        }
    }

    override fun getFavoriteTvShow(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavTvShows(), config).build()
    }

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavMovies(), config).build()
    }
}