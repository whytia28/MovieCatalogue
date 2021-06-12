package com.why.movieCatalogue.utils

import com.why.movieCatalogue.BuildConfig.BASE_URL
import com.why.movieCatalogue.data.source.MovieCatalogueRepository
import com.why.movieCatalogue.data.source.remote.RemoteDataSource
import com.why.movieCatalogue.network.ApiService
import com.why.movieCatalogue.ui.detail.DetailMovieViewModel
import com.why.movieCatalogue.ui.movie.MovieViewModel
import com.why.movieCatalogue.ui.tvShow.TvShowViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


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
    single { MovieCatalogueRepository(get()) }
    factory { RemoteDataSource(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }

}