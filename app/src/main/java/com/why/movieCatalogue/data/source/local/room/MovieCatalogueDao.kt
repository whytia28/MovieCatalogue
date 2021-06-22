package com.why.movieCatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.why.movieCatalogue.data.source.local.entity.MovieEntity
import com.why.movieCatalogue.data.source.local.entity.TvShowEntity

@Dao
interface MovieCatalogueDao {
    @Query("SELECT * FROM movieEntities")
    fun getAllMovies(): DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM tvShowEntities")
    fun getAllTvShow(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM movieEntities WHERE id = :id")
    fun getMovieById(id: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM tvShowEntities WHERE id = :id")
    fun getTvSHowById(id: Int): LiveData<TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movie: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(tvShow: List<TvShowEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)

    @Update
    fun updateTvShow(tvShow: TvShowEntity)

    @Query("SELECT * FROM tvShowEntities WHERE isFav = 1")
    fun getFavTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM movieEntities WHERE isFav = 1")
    fun getFavMovies(): DataSource.Factory<Int, MovieEntity>

}