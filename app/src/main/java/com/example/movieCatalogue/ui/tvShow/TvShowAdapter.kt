package com.example.movieCatalogue.ui.tvShow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieCatalogue.data.TvShowEntity
import com.example.movieCatalogue.databinding.ItemsTvShowBinding


class TvShowAdapter(private val callback: TvShowCallback) :
    RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private var onItemClickCallback: OnItemClickCallback? = null
    private var listTvShow = ArrayList<TvShowEntity>()

    fun setTvShows(tvShows: List<TvShowEntity>?) {
        if (tvShows == null) return
        this.listTvShow.clear()
        this.listTvShow.addAll(tvShows)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TvShowAdapter.TvShowViewHolder {
        val itemTvShowBinding =
            ItemsTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemTvShowBinding)
    }

    override fun onBindViewHolder(holder: TvShowAdapter.TvShowViewHolder, position: Int) {
        val tvShow = listTvShow[position]
        holder.bind(tvShow)
    }

    override fun getItemCount(): Int = listTvShow.size

    inner class TvShowViewHolder(private val binding: ItemsTvShowBinding) :
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
                Glide.with(itemView.context)
                    .load(tvShow.imagePoster)
                    .into(imgPosterTvShow)
            }
        }

    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TvShowEntity)
    }

    fun setOnItemClicked(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
}