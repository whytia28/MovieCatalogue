package com.why.movieCatalogue.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.why.movieCatalogue.data.source.local.entity.MovieEntity
import com.why.movieCatalogue.data.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class MovieCatalogueDatabase : RoomDatabase() {

    abstract fun movieCatalogueDao(): MovieCatalogueDao

    companion object {

        @Volatile
        private var INSTANCE: MovieCatalogueDatabase? = null

        fun getInstance(context: Context): MovieCatalogueDatabase =
            INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MovieCatalogueDatabase::class.java,
                    "MovieCatalogue.db"
                ).build().apply { INSTANCE = this }
            }
    }

}