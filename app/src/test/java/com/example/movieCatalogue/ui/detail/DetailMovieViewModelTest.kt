package com.example.movieCatalogue.ui.detail

import com.example.movieCatalogue.utils.DataMovie
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataMovie.generateDataMovie()[0]
    private val dummyTvShow = DataMovie.generateDataTvShow()[0]
    private val movieId = dummyMovie.movieId
    private val tvShowId = dummyTvShow.tvShowId

    @Before
    fun setup() {
        viewModel = DetailMovieViewModel()
        viewModel.setSelectedTvShow(movieId)
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(dummyMovie.movieId)
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.releaseDate, movieEntity.releaseDate)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.category, movieEntity.category)
        assertEquals(dummyMovie.synopsis, movieEntity.synopsis)
        assertEquals(dummyMovie.imagePoster, movieEntity.imagePoster)
    }

    @Test
    fun getTvShow() {
        viewModel.setSelectedTvShow(dummyTvShow.tvShowId)
        val tvShowEntity = viewModel.getTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.title, tvShowEntity.title)
        assertEquals(dummyTvShow.tvShowId, tvShowEntity.tvShowId)
        assertEquals(dummyTvShow.releaseDate, tvShowEntity.releaseDate)
        assertEquals(dummyTvShow.category, tvShowEntity.category)
        assertEquals(dummyTvShow.synopsis, tvShowEntity.synopsis)
        assertEquals(dummyTvShow.imagePoster, tvShowEntity.imagePoster)
    }
}