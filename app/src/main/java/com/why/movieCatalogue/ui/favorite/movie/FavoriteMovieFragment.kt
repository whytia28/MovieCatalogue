package com.why.movieCatalogue.ui.favorite.movie

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
import com.why.movieCatalogue.data.source.local.entity.MovieEntity
import com.why.movieCatalogue.databinding.FragmentFavoriteMovieBinding
import com.why.movieCatalogue.ui.detail.DetailMovieActivity
import com.why.movieCatalogue.ui.detail.DetailMovieViewModel.Companion.MOVIE
import com.why.movieCatalogue.ui.movie.MovieCallback
import org.koin.androidx.viewmodel.ext.android.viewModel


class FavoriteMovieFragment : Fragment(), MovieCallback {

    private val viewModel: FavoriteMovieViewModel by viewModel()
    private lateinit var adapter: FavoriteMovieAdapter
    private lateinit var binding: FragmentFavoriteMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.getFavMovie().observe(viewLifecycleOwner, { favMovie ->
            if (favMovie != null) {
                adapter.submitList(favMovie)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(binding.rvFavMovie)

        if (activity != null) {
            adapter = FavoriteMovieAdapter(this)

            viewModel.getFavMovie().observe(viewLifecycleOwner, { favMovie ->
                if (favMovie != null) {
                    adapter.submitList(favMovie)
                }
            })


            binding.rvFavMovie.layoutManager = LinearLayoutManager(context)
            binding.rvFavMovie.setHasFixedSize(true)
            binding.rvFavMovie.adapter = adapter


            adapter.setOnItemClickCallback(object : FavoriteMovieAdapter.OnItemClickCallback {
                override fun onItemClicked(data: MovieEntity) {
                    activity?.let {
                        val intent = Intent(it, DetailMovieActivity::class.java)
                        intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, data.id)
                        intent.putExtra(DetailMovieActivity.EXTRA_CATEGORY, MOVIE)
                        it.startActivity(intent)
                        activity?.overridePendingTransition(R.anim.from_right, R.anim.to_left)
                    }
                }

            })
        }
    }

    override fun onShareClick(movie: MovieEntity) {
        if (activity != null) {
            val mimetype = "text/plain"
            ShareCompat.IntentBuilder
                .from(requireActivity())
                .setType(mimetype)
                .setChooserTitle(resources.getString(R.string.share_title))
                .setText(resources.getString(R.string.share_text, movie.title))
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
                val movie = adapter.getSwipedData(swipedPosition)
                movie?.let { viewModel.setFavMovie(it) }

                val snackBar = Snackbar.make(requireView(), R.string.undo, Snackbar.LENGTH_LONG)
                snackBar.setAction(R.string.ok) { _ ->
                    movie?.let { viewModel.setFavMovie(it) }
                }
                snackBar.show()
            }
        }

    })

}