package com.example.movieCatalogue.ui.movie

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieCatalogue.R
import com.example.movieCatalogue.data.MovieEntity
import com.example.movieCatalogue.databinding.FragmentMovieBinding
import com.example.movieCatalogue.ui.detail.DetailMovieActivity
import org.koin.androidx.viewmodel.ext.android.viewModel


class MovieFragment : Fragment(), MovieCallback {

    private val viewModel: MovieViewModel by viewModel()
    private lateinit var fragmentMovieBinding: FragmentMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fragmentMovieBinding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return fragmentMovieBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val movieAdapter = MovieAdapter(this)
            viewModel.getMovies().observe(viewLifecycleOwner, { movies ->
                movieAdapter.setMovies(movies)
                movieAdapter.notifyDataSetChanged()
            })

            with(fragmentMovieBinding.rvMovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = movieAdapter
            }
            movieAdapter.setOnItemClickCallback(object : MovieAdapter.OnItemClickCallback {
                override fun onItemClicked(data: MovieEntity) {
                    activity?.let {
                        val intent = Intent(it, DetailMovieActivity::class.java)
                        intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, data.movieId)
                        it.startActivity(intent)
                    }
                }

            })

            viewModel.getLoading().observe(viewLifecycleOwner, {
                fragmentMovieBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
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


}