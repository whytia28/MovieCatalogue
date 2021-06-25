package com.why.movieCatalogue.ui.favorite.tvShow

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
import com.why.movieCatalogue.data.source.local.entity.TvShowEntity
import com.why.movieCatalogue.databinding.ItemsMovieBinding
import com.why.movieCatalogue.ui.tvShow.TvShowCallback

class FavoriteTvShowAdapter(private val callback: TvShowCallback) :
    PagedListAdapter<TvShowEntity, FavoriteTvShowAdapter.MovieViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    private var onItemClickCallback: OnItemClickCallback? = null

    fun getSwipedData(swipedPosition: Int): TvShowEntity? = getItem(swipedPosition)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemMovieBinding =
            ItemsMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val tvShow = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    inner class MovieViewHolder(private val binding: ItemsMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
            fun bind(tvShow: TvShowEntity) {
                with(binding) {
                    tvItemTitle.text = tvShow.title
                    tvItemRelease.text = tvShow.releaseDate
                    tvItemSynopsis.text = tvShow.synopsis
                    itemView.setOnClickListener {
                        onItemClickCallback?.onItemClicked(tvShow)
                    }
                    imgShare.setOnClickListener { callback.onShareClick(tvShow) }
                    Glide.with(root.context)
                        .asBitmap()
                        .load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/${tvShow.imagePoster}")
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
        fun onItemClicked(data: TvShowEntity)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}