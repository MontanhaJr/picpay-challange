package com.picpay.desafio.android

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest

class MainActivityTest : KoinTest {

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setUp() {
        stopKoin()
        startKoin {
            modules(testModule)
        }
    }

    @After
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun shouldDisplayTitle() {
        launchActivity<MainActivity>().apply {
            val expectedTitle = context.getString(R.string.title)
            moveToState(Lifecycle.State.RESUMED)
            onView(withText(expectedTitle)).check(matches(isDisplayed()))
        }
    }

    @Test
    fun shouldDisplayListItem() {
        launchActivity<MainActivity>().apply {
            moveToState(Lifecycle.State.RESUMED)
            onView(withText("João")).check(matches(isDisplayed()))
            onView(withText("Maria")).check(matches(isDisplayed()))
        }
    }

    @Test
    fun shouldDisplayCorrectUserNamesInRecyclerView() {
        launchActivity<MainActivity>().apply {
            moveToState(Lifecycle.State.RESUMED)

            RecyclerViewMatchers.checkRecyclerViewItem(
                resId = R.id.recyclerView,
                position = 0,
                withMatcher = withText("João")
            )

            RecyclerViewMatchers.checkRecyclerViewItem(
                resId = R.id.recyclerView,
                position = 1,
                withMatcher = withText("Maria")
            )
        }
    }

}
