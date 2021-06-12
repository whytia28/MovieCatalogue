package com.why.movieCatalogue.ui.tvShow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.why.movieCatalogue.R
import com.why.movieCatalogue.data.TvShowEntity
import com.why.movieCatalogue.databinding.FragmentTvShowBinding
import com.why.movieCatalogue.ui.detail.DetailMovieActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class TvShowFragment : Fragment(), TvShowCallback {

    private val viewModel: TvShowViewModel by viewModel()
    private lateinit var fragmentTvShowBinding: FragmentTvShowBinding

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
            val adapter = TvShowAdapter(this)
            viewModel.getTvShows().observe(viewLifecycleOwner, { tvShows ->
                adapter.setTvShows(tvShows)
                adapter.notifyDataSetChanged()
            })

            with(fragmentTvShowBinding.rvTvShow) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = adapter
            }
            adapter.setOnItemClicked(object : TvShowAdapter.OnItemClickCallback {
                override fun onItemClicked(data: TvShowEntity) {
                    activity?.let {
                        val intent = Intent(it, DetailMovieActivity::class.java)
                        intent.putExtra(DetailMovieActivity.EXTRA_TV_SHOW, data.tvShowId)
                        it.startActivity(intent)
                    }
                }

            })

        }

        viewModel.getLoading().observe(viewLifecycleOwner, {
            fragmentTvShowBinding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
        })
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