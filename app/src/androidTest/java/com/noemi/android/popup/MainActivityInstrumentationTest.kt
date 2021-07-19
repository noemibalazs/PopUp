package com.noemi.android.popup

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import com.noemi.android.popup.adapter.*
import com.noemi.android.popup.api.model.Artwork
import com.noemi.android.popup.ui.MainActivity
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityInstrumentationTest {


    @get:Rule
    val rule = activityScenarioRule<MainActivity>()

    @Test
    fun testAppContext() {
        val context = InstrumentationRegistry.getInstrumentation().context
        Assert.assertEquals("com.noemi.android.popup.test", context.packageName)
    }

    @Test
    fun testBusinessLogic() {
        val artwork1 = Artwork(
            id = 12,
            tags = "Horse",
            user = "Black Night",
            url = "https://pixabay.com/hu/photos/l%c3%a1ny-%c3%a1lmodoz%c3%a1s-l%c3%b3-%c3%a1lmodozik-n%c5%91-3551832/"
        )

        val artwork2 = Artwork(
            id = 21,
            tags = "Horse",
            user = "Black Thunder",
            url = "https://pixabay.com/hu/photos/l%c3%a1ny-%c3%a1lmodoz%c3%a1s-l%c3%b3-%c3%a1lmodozik-n%c5%91-3551832/"
        )
        val artworks = mutableListOf(artwork1, artwork2)
        val listener: PixabayListener = {}
        val adapter = PixabayAdapter(listener)

        rule.scenario.onActivity {
            it.findViewById<RecyclerView>(R.id.rvArtworks).adapter = adapter
            adapter.submitList(artworks)
        }

        onView(withId(R.id.progress)).check(matches(isDisplayed()))

        onView(withId(R.id.rvArtworks))
            .check(matches(isDisplayed())).perform(
                RecyclerViewActions.actionOnItemAtPosition<PixabayVH>(
                    0,
                    click()
                )
            )
    }
}