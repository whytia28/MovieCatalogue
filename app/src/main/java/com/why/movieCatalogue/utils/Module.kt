package com.why.movieCatalogue.utils

import android.annotation.SuppressLint
import android.app.Application
import androidx.room.Room
import com.why.movieCatalogue.BuildConfig.BASE_URL
import com.why.movieCatalogue.data.MovieCatalogueRepository
import com.why.movieCatalogue.data.source.local.LocalDataSource
import com.why.movieCatalogue.data.source.local.room.MovieCatalogueDao
import com.why.movieCatalogue.data.source.local.room.MovieCatalogueDatabase
import com.why.movieCatalogue.data.source.remote.RemoteDataSource
import com.why.movieCatalogue.network.ApiService
import com.why.movieCatalogue.ui.detail.DetailMovieViewModel
import com.why.movieCatalogue.ui.favorite.movie.FavoriteMovieViewModel
import com.why.movieCatalogue.ui.favorite.tvShow.FavoriteTvShowViewModel
import com.why.movieCatalogue.ui.movie.MovieViewModel
import com.why.movieCatalogue.ui.tvShow.TvShowViewModel
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.Executors


@SuppressLint("VisibleForTests")
val appModule = module {
    single {
        OkHttpClient.Builder()
            .build()
    }
    factory<ApiService> {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()

        retrofit.create(ApiService::class.java)
    }

    fun provideDatabase(application: Application): MovieCatalogueDatabase {
        return Room.databaseBuilder(
            application,
            MovieCatalogueDatabase::class.java,
            "MovieCatalogue.db"
        ).build()
    }

    fun provideDao(database: MovieCatalogueDatabase): MovieCatalogueDao {
        return database.movieCatalogueDao()
    }
    single { AppExecutors(
        Executors.newSingleThreadExecutor(),
        Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() + 1))
    }
    single { provideDatabase(androidApplication()) }
    single { provideDao(get()) }
    factory { LocalDataSource(get()) }
    factory { MovieCatalogueRepository(get(), get(), get()) }
    factory { RemoteDataSource(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { FavoriteMovieViewModel(get()) }
    viewModel { FavoriteTvShowViewModel(get()) }

}