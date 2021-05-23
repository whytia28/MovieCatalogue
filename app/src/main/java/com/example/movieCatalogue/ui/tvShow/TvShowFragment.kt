package com.example.movieCatalogue.ui.tvShow

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.app.ShareCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieCatalogue.R
import com.example.movieCatalogue.data.TvShowEntity
import com.example.movieCatalogue.databinding.FragmentTvShowBinding
import com.example.movieCatalogue.ui.detail.DetailMovieActivity

class TvShowFragment : Fragment(), TvShowCallback {

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
            val viewModel = ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            )[TvShowViewModel::class.java]
            val tvShows = viewModel.getTvShows()
            val adapter = TvShowAdapter(this)
            adapter.setTvShows(tvShows)

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