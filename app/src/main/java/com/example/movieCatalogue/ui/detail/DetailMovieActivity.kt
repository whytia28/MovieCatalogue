package com.example.movieCatalogue.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.movieCatalogue.data.MovieEntity
import com.example.movieCatalogue.data.TvShowEntity
import com.example.movieCatalogue.databinding.ActivityDetailMovieBinding
import com.example.movieCatalogue.databinding.ContentDetailMovieBinding

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SHOW = "extra_tv_show"
    }

    private lateinit var detailContentBinding: ContentDetailMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val activityDetailMovieBinding = ActivityDetailMovieBinding.inflate(layoutInflater)
        detailContentBinding = activityDetailMovieBinding.detailContent

        setContentView(activityDetailMovieBinding.root)

        setSupportActionBar(activityDetailMovieBinding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val viewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        )[DetailMovieViewModel::class.java]

        val extras = intent.extras
        if (extras != null) {
            val movieId = extras.getString(EXTRA_MOVIE)
            val tvShowId = extras.getString(EXTRA_TV_SHOW)
            if (movieId != null) {
                viewModel.setSelectedMovie(movieId)
                populateMovie(viewModel.getMovie())
            }
            if (tvShowId != null) {
                viewModel.setSelectedTvShow(tvShowId)
                populateMovie(viewModel.getTvShow())
            }
        }
    }

    private fun populateMovie(movieEntity: MovieEntity) {
        detailContentBinding.textTitle.text = movieEntity.title
        detailContentBinding.textRelease.text = movieEntity.releaseDate
        detailContentBinding.textCategory.text = movieEntity.category
        detailContentBinding.textSynopsis.text = movieEntity.synopsis

        Glide.with(this)
            .load(movieEntity.imagePoster)
            .into(detailContentBinding.imagePoster)
    }

    private fun populateMovie(tvShowEntity: TvShowEntity) {
        detailContentBinding.textTitle.text = tvShowEntity.title
        detailContentBinding.textRelease.text = tvShowEntity.releaseDate
        detailContentBinding.textCategory.text = tvShowEntity.category
        detailContentBinding.textSynopsis.text = tvShowEntity.synopsis

        Glide.with(this)
            .load(tvShowEntity.imagePoster)
            .into(detailContentBinding.imagePoster)
    }

}