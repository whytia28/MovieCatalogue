package com.why.movieCatalogue.ui.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.why.movieCatalogue.R
import com.why.movieCatalogue.databinding.FragmentFavoriteBinding
import com.why.movieCatalogue.ui.favorite.movie.FavoriteMovieFragment
import com.why.movieCatalogue.ui.favorite.tvShow.FavoriteTvShowFragment
import com.why.movieCatalogue.ui.home.HomeActivity


class FavoriteFragment : Fragment() {

    private lateinit var favoriteFragmentBinding: FragmentFavoriteBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        favoriteFragmentBinding = FragmentFavoriteBinding.inflate(layoutInflater, container, false)
        return favoriteFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            (activity as HomeActivity).setActionBarTitle(getString(R.string.favorite))

            setViewPager()
        }
    }

    private fun setViewPager() {
        val fragmentList = listOf(FavoriteMovieFragment(), FavoriteTvShowFragment())
        val tabTitle = listOf(
            resources.getString(R.string.favorite_movie),
            resources.getString(R.string.favorite_tv_show)
        )
        favoriteFragmentBinding.viewPager.adapter =
            ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)

        TabLayoutMediator(
            favoriteFragmentBinding.tabLayoutFav,
            favoriteFragmentBinding.viewPager
        ) { tab, position ->
            tab.text = tabTitle[position]
        }.attach()
    }


}