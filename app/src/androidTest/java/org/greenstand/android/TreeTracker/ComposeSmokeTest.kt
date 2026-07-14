package org.greenstand.android.TreeTracker
import androidx.compose.material.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ComposeSmokeTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun compose_harness_displays_content() {
        composeTestRule.setContent {
            Text("test")
        }
        composeTestRule.onNodeWithText("test").assertIsDisplayed()
    }
}