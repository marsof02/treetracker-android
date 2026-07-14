package org.greenstand.android.TreeTracker.dashboard

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.greenstand.android.TreeTracker.utils.PreviewDependencies
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DashboardInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun dashboard_shows_tree_counts() {
        composeTestRule.setContent {
            CompositionLocalProvider(LocalInspectionMode provides true) {
                PreviewDependencies {
                    Dashboard(
                        state = DashboardState(
                            treesRemainingToSync = 51,
                            treesSynced = 146,
                            totalTreesToSync = 200,
                        )
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("146").assertIsDisplayed()
        composeTestRule.onNodeWithText("51").assertIsDisplayed()

        composeTestRule.onNodeWithText("UPLOAD").assertIsDisplayed()

    }

}