package com.why.movieCatalogue.ui.detail

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.why.movieCatalogue.R
import com.why.movieCatalogue.data.source.local.entity.MovieEntity
import com.why.movieCatalogue.data.source.local.entity.TvShowEntity
import com.why.movieCatalogue.databinding.ActivityDetailMovieBinding
import com.why.movieCatalogue.databinding.ContentDetailMovieBinding
import com.why.movieCatalogue.ui.detail.DetailMovieViewModel.Companion.MOVIE
import com.why.movieCatalogue.ui.detail.DetailMovieViewModel.Companion.TV_SHOW
import com.why.movieCatalogue.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailMovieActivity : AppCompatActivity() {

    companion object {
        const val EXTRA_MOVIE = "extra_movie"
        const val EXTRA_TV_SHOW = "extra_tv_show"
        const val EXTRA_CATEGORY = "extra_category"
    }

    private val viewModel: DetailMovieViewModel by viewModel()

    private lateinit var detailContentBinding: ContentDetailMovieBinding
    private var category: String? = null
    private var menu: Menu? = null


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
            category = extras.getString(EXTRA_CATEGORY)

            viewModel.setSelectedTvShow(tvShowId)
            viewModel.setSelectedMovie(movieId)

            if (category != null) {
                viewModel.setDetail(category.toString())
                if (category == MOVIE) {
                    viewModel.getDetailMovie().observe(this, { movie ->
                        if (movie != null) {
                            when (movie.status) {
                                Status.LOADING -> showProgressBar(true)
                                Status.SUCCESS -> {
                                    if (movie.data != null) {
                                        showProgressBar(false)
                                        populateTvShow(movie.data)
                                    }
                                }
                                Status.ERROR -> {
                                    showProgressBar(false)
                                    Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                                }
                            }
                        }
                    })

                } else if (category == TV_SHOW) {
                    viewModel.getDetailTvShow()
                        .observe(this, { tvShow ->
                            if (tvShow != null) {
                                when (tvShow.status) {
                                    Status.LOADING -> showProgressBar(true)
                                    Status.SUCCESS -> {
                                        showProgressBar(false)
                                        tvShow.data?.let { populateTvShow(it) }
                                    }
                                    Status.ERROR -> {
                                        showProgressBar(false)
                                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                                    }
                                }
                            }
                        })
                }
            }
        }

    }

    override fun finish() {
        overridePendingTransition(R.anim.from_left, R.anim.to_right)
        super.finish()
    }

    override fun onBackPressed() {
        finish()
        super.onBackPressed()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        overridePendingTransition(R.anim.from_left, R.anim.to_right)
        return true
    }

    private fun populateTvShow(movieEntity: MovieEntity) {
        detailContentBinding.textTitle.text = movieEntity.title
        detailContentBinding.textRelease.text = movieEntity.releaseDate
        detailContentBinding.textCategory.text = movieEntity.category
        detailContentBinding.textSynopsis.text = movieEntity.synopsis

        Glide.with(this)
            .asBitmap()
            .apply(RequestOptions.placeholderOf(R.drawable.ic_movie_placeholder))
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/${movieEntity.imagePoster}")
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    detailContentBinding.imagePoster.setImageBitmap(resource)
                    setColorByPalette(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
    }

    private fun populateTvShow(tvShowEntity: TvShowEntity) {
        detailContentBinding.textTitle.text = tvShowEntity.title
        detailContentBinding.textRelease.text = tvShowEntity.releaseDate
        detailContentBinding.textCategory.text = tvShowEntity.category
        detailContentBinding.textSynopsis.text = tvShowEntity.synopsis

        Glide.with(this)
            .asBitmap()
            .apply(RequestOptions.placeholderOf(R.drawable.ic_movie_placeholder))
            .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/${tvShowEntity.imagePoster}")
            .into(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    detailContentBinding.imagePoster.setImageBitmap(resource)
                    setColorByPalette(resource)
                }

                override fun onLoadCleared(placeholder: Drawable?) {
                }
            })
    }

    private fun showProgressBar(state: Boolean) {
        detailContentBinding.progressBar.isVisible = state
    }

    private fun setColorByPalette(poster: Bitmap) {
        Palette.from(poster).generate() { palette ->
            val defValue = resources.getColor(R.color.dark, theme)
            detailContentBinding.viewPoster.setBackgroundColor(
                palette?.getDarkMutedColor(defValue) ?: defValue
            )

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        this.menu = menu
        if (category == MOVIE) {
            viewModel.getDetailMovie().observe(this, { movie ->
                when (movie.status) {
                    Status.LOADING -> showProgressBar(true)
                    Status.SUCCESS -> {
                        if (movie.data != null) {
                            showProgressBar(false)
                            val state = movie.data.isFav
                            setFavoriteState(state)
                        }
                    }
                    Status.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        } else if (category == TV_SHOW) {
            viewModel.getDetailTvShow().observe(this, { tvShow ->
                when (tvShow.status) {
                    Status.LOADING -> showProgressBar(true)
                    Status.SUCCESS -> {
                        if (tvShow.data != null) {
                            showProgressBar(false)
                            val state = tvShow.data.isFav
                            setFavoriteState(state)
                        }
                    }
                    Status.ERROR -> {
                        showProgressBar(false)
                        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_favorite) {
            if (category == MOVIE) {
                viewModel.setFavoriteMovie()
            } else if (category == TV_SHOW) {
                viewModel.setFavoriteTvShow()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setFavoriteState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_favorite)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite)
        } else {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_favorite_white)
        }
    }

}