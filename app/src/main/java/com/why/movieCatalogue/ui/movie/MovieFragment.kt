package com.why.movieCatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import com.why.movieCatalogue.R
import com.why.movieCatalogue.data.source.local.entity.MovieEntity
import com.why.movieCatalogue.databinding.FragmentMovieBinding
import com.why.movieCatalogue.ui.detail.DetailMovieActivity
import com.why.movieCatalogue.ui.detail.DetailMovieViewModel.Companion.MOVIE
import com.why.movieCatalogue.ui.home.HomeActivity
import com.why.movieCatalogue.utils.SortUtils.ASCEND
import com.why.movieCatalogue.utils.SortUtils.DESCEND
import com.why.movieCatalogue.utils.SortUtils.RANDOM
import com.why.movieCatalogue.vo.Resource
import com.why.movieCatalogue.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFragment : Fragment(), MovieCallback {

    private val viewModel: MovieViewModel by viewModel()
    private lateinit var fragmentMovieBinding: FragmentMovieBinding
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        setHasOptionsMenu(true)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            (activity as HomeActivity).setActionBarTitle(getString(R.string.movie))

            movieAdapter = MovieAdapter(this)
            viewModel.getMovies(ASCEND).observe(viewLifecycleOwner, movieObserver)

            with(fragmentMovieBinding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = movieAdapter
            }
            movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
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

    private val movieObserver = Observer<Resource<PagedList<MovieEntity>>> { movies ->
        if (movies != null) {
            when (movies.status) {
                Status.LOADING -> showProgressBar(true)
                Status.SUCCESS -> {
                    movieAdapter.submitList(movies.data)
                    movieAdapter.notifyDataSetChanged()
                    showProgressBar(false)
                }
                Status.ERROR -> Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.menu_sort, menu)
        return super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var sort = ""
        when (item.itemId) {
            R.id.action_asc -> sort = ASCEND
            R.id.action_desc -> sort = DESCEND
            R.id.action_random -> sort = RANDOM
        }
        viewModel.getMovies(sort).observe(viewLifecycleOwner, movieObserver)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
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

    private fun showProgressBar(state: Boolean) {
        fragmentMovieBinding.progressBar.isVisible = state
        fragmentMovieBinding.rvMovie.isInvisible = state
    }

}