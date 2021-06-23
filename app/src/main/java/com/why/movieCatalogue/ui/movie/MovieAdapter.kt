package com.why.movieCatalogue.ui.movie

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.palette.graphics.Palette
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.why.movieCatalogue.R
import com.why.movieCatalogue.data.source.local.entity.MovieEntity
import com.why.movieCatalogue.databinding.ItemsMovieBinding

class MovieAdapter(private val callback: MovieCallback) :
    PagedListAdapter<MovieEntity, MovieAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding =
            ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }


    inner class MovieViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvItemTitle.text = movie.title
                tvItemRelease.text = movie.releaseDate
                tvItemSynopsis.text = movie.synopsis
                itemView.setOnClickListener {
                    onItemClickCallback?.onItemClicked(movie)
                }
                imgShare.setOnClickListener { callback.onShareClick(movie) }
                Glide.with(root.context)
                    .asBitmap()
                    .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/${movie.imagePoster}")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_movie_placeholder))
                    .transform(RoundedCorners(28))
                    .into(object : CustomTarget<Bitmap>() {
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap>?
                        ) {
                            imgPoster.setImageBitmap(resource)

                            Palette.from(resource).generate { palette ->
                                val defValue = itemView.resources.getColor(
                                    R.color.dark,
                                    itemView.context.theme
                                )
                                itemCard.setCardBackgroundColor(
                                    palette?.getDarkMutedColor(defValue) ?: defValue
                                )

                            }
                        }

                        override fun onLoadCleared(placeholder: Drawable?) {
                        }
                    })
            }
        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: MovieEntity)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}