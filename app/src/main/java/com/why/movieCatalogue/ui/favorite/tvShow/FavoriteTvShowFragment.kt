package com.why.movieCatalogue.ui.favorite.tvShow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.why.movieCatalogue.R
import com.why.movieCatalogue.data.source.local.entity.TvShowEntity
import com.why.movieCatalogue.databinding.FragmentFavoriteTvShowBinding
import com.why.movieCatalogue.ui.detail.DetailMovieActivity
import com.why.movieCatalogue.ui.detail.DetailMovieViewModel
import com.why.movieCatalogue.ui.tvShow.TvShowCallback
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteTvShowFragment : Fragment(), TvShowCallback {

    private val viewModel: FavoriteTvShowViewModel by viewModel()
    private lateinit var binding: FragmentFavoriteTvShowBinding
    private lateinit var adapter: FavoriteTvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavTvShow().observe(viewLifecycleOwner, { favTvShow ->
            adapter.submitList(favTvShow)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.rvFavTvShow)

        if (activity != null) {
            adapter = FavoriteTvShowAdapter(this)
            viewModel.getFavTvShow().observe(viewLifecycleOwner, { favTvShow ->
                if (favTvShow != null) {
                    adapter.submitList(favTvShow)
                }
            })


            binding.rvFavTvShow.layoutManager = LinearLayoutManager(context)
            binding.rvFavTvShow.setHasFixedSize(true)
            binding.rvFavTvShow.adapter = adapter

            adapter.setOnItemClickCallback(object : FavoriteTvShowAdapter.OnItemClickCallback {
                override fun onItemClicked(data: TvShowEntity) {
                    activity?.let {
                        val intent = Intent(it, DetailMovieActivity::class.java)
                        intent.putExtra(DetailMovieActivity.EXTRA_TV_SHOW, data.id)
                        intent.putExtra(
                            DetailMovieActivity.EXTRA_CATEGORY,
                            DetailMovieViewModel.TV_SHOW
                        )
                        it.startActivity(intent)
                        activity?.overridePendingTransition(R.anim.from_right, R.anim.to_left)
                    }
                }

            })
        }

    }

    override fun onShareClick(tvShow: TvShowEntity) {
        if (activity != null) {
            val mimetype = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimetype)
                .setChooserTitle(resources.getString(R.string.share_title))
                .setText(resources.getString(R.string.share_text, tvShow.title))
                .startChooser()
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.bindingAdapterPosition
                val tvShow = adapter.getSwipedData(swipedPosition)
                tvShow?.let { viewModel.setFavTvShow(it) }

                val snackBar = Snackbar.make(requireView(), R.string.undo, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.ok) { _ ->
                    tvShow?.let { viewModel.setFavTvShow(it) }
                }
                snackBar.show()
            }
        }

    })

}