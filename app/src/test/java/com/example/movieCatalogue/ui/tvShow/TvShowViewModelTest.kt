package com.example.movieCatalogue.ui.tvShow

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class TvShowViewModelTest {
    private lateinit var viewModel: TvShowViewModel

    @Before
    fun setup() {
        viewModel = TvShowViewModel()
    }

    @Test
    fun getTvShows() {
        val tvShowEntity = viewModel.getTvShows()
        assertNotNull(tvShowEntity)
        assertEquals(12, tvShowEntity.size)
    }
}