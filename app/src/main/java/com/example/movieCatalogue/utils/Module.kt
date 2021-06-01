package com.example.movieCatalogue.utils


import com.example.movieCatalogue.data.source.MovieRepository
import com.example.movieCatalogue.network.ApiService
import com.example.movieCatalogue.ui.detail.DetailMovieViewModel
import com.example.movieCatalogue.ui.movie.MovieViewModel
import com.example.movieCatalogue.ui.tvShow.TvShowViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


const val BASE_URL = "https://api.themoviedb.org/3/"

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
    single { MovieRepository(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }

}