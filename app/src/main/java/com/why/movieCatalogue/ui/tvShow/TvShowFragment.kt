package com.why.movieCatalogue.ui.tvShow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.why.movieCatalogue.R
import com.why.movieCatalogue.data.source.local.entity.TvShowEntity
import com.why.movieCatalogue.databinding.FragmentTvShowBinding
import com.why.movieCatalogue.ui.detail.DetailMovieActivity
import com.why.movieCatalogue.ui.detail.DetailMovieViewModel.Companion.TV_SHOW
import com.why.movieCatalogue.ui.home.HomeActivity
import com.why.movieCatalogue.vo.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment(), TvShowCallback {

    private val viewModel: TvShowViewModel by viewModel()
    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding
    private lateinit var adapter: TvShowAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        fragmentTvShowBinding = FragmentTvShowBinding.inflate(inflater, container, false)
        return fragmentTvShowBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            (activity as HomeActivity).setActionBarTitle(getString(R.string.tv_show))

            adapter = TvShowAdapter(this)
            viewModel.getTvShows().observe(viewLifecycleOwner, { tvShows ->
                if (tvShows != null) {
                    when (tvShows.status) {
                        Status.LOADING -> fragmentTvShowBinding.progressBar.visibility =
                            View.VISIBLE
                        Status.SUCCESS -> {
                            adapter.submitList(tvShows.data)
                            adapter.notifyDataSetChanged()
                            fragmentTvShowBinding.progressBar.visibility = View.GONE
                        }
                        Status.ERROR -> Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
                    }
                }
            })

            fragmentTvShowBinding.rvTvShow.layoutManager = LinearLayoutManager(context)
            fragmentTvShowBinding.rvTvShow.setHasFixedSize(true)
            fragmentTvShowBinding.rvTvShow.adapter = adapter

            adapter.setOnItemClicked(object : TvShowAdapter.OnItemClickCallback {
                override fun onItemClicked(data: TvShowEntity) {
                    activity?.let {
                        val intent = Intent(it, DetailMovieActivity::class.java)
                        intent.putExtra(DetailMovieActivity.EXTRA_TV_SHOW, data.id)
                        intent.putExtra(DetailMovieActivity.EXTRA_CATEGORY, TV_SHOW)
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


}