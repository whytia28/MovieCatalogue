package com.why.movieCatalogue.ui.tvShow

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
import com.why.movieCatalogue.data.source.local.entity.TvShowEntity
import com.why.movieCatalogue.databinding.FragmentTvShowBinding
import com.why.movieCatalogue.ui.detail.DetailMovieActivity
import com.why.movieCatalogue.ui.detail.DetailMovieViewModel.Companion.TV_SHOW
import com.why.movieCatalogue.ui.home.HomeActivity
import com.why.movieCatalogue.utils.SortUtils.ASCEND
import com.why.movieCatalogue.utils.SortUtils.DESCEND
import com.why.movieCatalogue.utils.SortUtils.RANDOM
import com.why.movieCatalogue.vo.Resource
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
        setHasOptionsMenu(true)
        return fragmentTvShowBinding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            (activity as HomeActivity).setActionBarTitle(getString(R.string.tv_show))

            adapter = TvShowAdapter(this)
            viewModel.getTvShows(ASCEND).observe(viewLifecycleOwner, tvShowObserver)

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

    private val tvShowObserver = Observer<Resource<PagedList<TvShowEntity>>> { tvShows ->
        if (tvShows != null) {
            when (tvShows.status) {
                Status.LOADING -> showProgressBar(true)
                Status.SUCCESS -> {
                    adapter.submitList(tvShows.data)
                    adapter.notifyDataSetChanged()
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
        viewModel.getTvShows(sort).observe(viewLifecycleOwner, tvShowObserver)
        item.isChecked = true
        return super.onOptionsItemSelected(item)
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

    private fun showProgressBar(state: Boolean) {
        fragmentTvShowBinding.progressBar.isVisible = state
        fragmentTvShowBinding.rvTvShow.isInvisible = state
    }
}