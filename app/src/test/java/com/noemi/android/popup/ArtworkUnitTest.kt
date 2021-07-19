package com.noemi.android.popup

import android.os.Build
import com.noemi.android.popup.api.model.Artwork
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
@RunWith(RobolectricTestRunner::class)
class ArtworkUnitTest {

    private val artWork = Artwork(
        id = 12,
        tags = "Horse",
        user = "Black Night",
        url = "https://pixabay.com/hu/photos/l%c3%a1ny-%c3%a1lmodoz%c3%a1s-l%c3%b3-%c3%a1lmodozik-n%c5%91-3551832/"
    )

    @Test
    fun testShouldPass() {
        val expected = Artwork(
            id = 12,
            tags = "Horse",
            user = "Black Night",
            url = "https://pixabay.com/hu/photos/l%c3%a1ny-%c3%a1lmodoz%c3%a1s-l%c3%b3-%c3%a1lmodozik-n%c5%91-3551832/"
        )
        assertEquals(expected, artWork)
    }

    @Test
    fun testShouldFail() {
        val expected = Artwork(
            id = 21,
            tags = "Horse",
            user = "Black Thunder",
            url = "https://pixabay.com/hu/photos/l%c3%a1ny-%c3%a1lmodoz%c3%a1s-l%c3%b3-%c3%a1lmodozik-n%c5%91-3551832/"
        )
        assertNotEquals(expected, artWork)
    }
}