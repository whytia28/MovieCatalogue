package com.example.movieCatalogue.ui.detail

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.movieCatalogue.data.MovieEntity
import com.example.movieCatalogue.data.TvShowEntity
import com.example.movieCatalogue.databinding.ActivityDetailMovieBinding
import com.example.movieCatalogue.databinding.ContentDetailMovieBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    private val viewModel: DetailMovieViewModel by viewModel()

    private lateinit var detailContentBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailMovieBinding.detailContent

        setContentView(activityDetailMovieBinding.root)

        setSupportActionBar(activityDetailMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getInt(EXTRA_MOVIE)
            val tvShowId = extras.getInt(EXTRA_TV_SHOW)

            viewModel.setSelectedMovie(movieId)
            viewModel.getMovie().observe(this, { movie -> populateMovie(movie) })

            viewModel.setSelectedTvShow(tvShowId)
            viewModel.getTvShow().observe(this, { tvShow -> populateMovie(tvShow) })

        }
        viewModel.getLoading().observe(this, {
            detailContentBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
    }

    private fun populateMovie(movieEntity: MovieEntity) {
        detailContentBinding.textTitle.text = movieEntity.title
        detailContentBinding.textRelease.text = movieEntity.releaseDate
        detailContentBinding.textCategory.text = movieEntity.category
        detailContentBinding.textSynopsis.text = movieEntity.synopsis

        Glide.with(this)
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/${movieEntity.imagePoster}")
            .into(detailContentBinding.imagePoster)
    }

    private fun populateMovie(tvShowEntity: TvShowEntity) {
        detailContentBinding.textTitle.text = tvShowEntity.title
        detailContentBinding.textRelease.text = tvShowEntity.releaseDate
        detailContentBinding.textCategory.text = tvShowEntity.category
        detailContentBinding.textSynopsis.text = tvShowEntity.synopsis

        Glide.with(this)
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/${tvShowEntity.imagePoster}")
            .into(detailContentBinding.imagePoster)
    }

}