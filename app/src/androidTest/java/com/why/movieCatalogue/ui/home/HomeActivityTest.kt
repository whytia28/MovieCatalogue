package com.why.movieCatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import com.why.movieCatalogue.R
import com.why.movieCatalogue.utils.DataMovie
import com.why.movieCatalogue.utils.EspressoIdlingResource
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Test

class HomeActivityTest {
    private val dummyMovie = DataMovie.generateDataMovie()
    private val dummyTvShow = DataMovie.generateDataTvShow()


    @Before
    fun setup(){
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun loadMovie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun loadTvShows() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_show)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTvShow.size
            )
        )
    }

    @Test
    fun loadDetailMovie() {
        onView(allOf(withId(R.id.rv_movie))).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.text_category)).check(matches(isDisplayed()))
        onView(withId(R.id.text_category)).check(matches(withText(dummyMovie[0].category.toString())))
        onView(withId(R.id.text_synopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.text_synopsis)).check(matches(withText(dummyMovie[0].synopsis)))
        onView(withId(R.id.text_release)).check(matches(isDisplayed()))
        onView(withId(R.id.text_release)).check(matches(withText(dummyMovie[0].releaseDate)))
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
    }

    @Test
    fun loadTvShowDetail() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv_show)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                3,
                click()
            )
        )
        onView(withId(R.id.text_title)).check(matches(isDisplayed()))
        onView(withId(R.id.text_title)).check(matches(withText(dummyTvShow[2].title)))
        onView(withId(R.id.text_category)).check(matches(isDisplayed()))
        onView(withId(R.id.text_category)).check(matches(withText(dummyTvShow[2].category.toString())))
        onView(withId(R.id.text_synopsis)).check(matches(isDisplayed()))
        onView(withId(R.id.text_synopsis)).check(matches(withText(dummyTvShow[2].synopsis)))
        onView(withId(R.id.text_release)).check(matches(isDisplayed()))
        onView(withId(R.id.text_release)).check(matches(withText(dummyTvShow[2].releaseDate)))
        onView(withId(R.id.image_poster)).check(matches(isDisplayed()))
    }
}