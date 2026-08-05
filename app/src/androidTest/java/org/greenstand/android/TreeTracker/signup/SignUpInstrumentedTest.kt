package org.greenstand.android.TreeTracker.signup

import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import junit.framework.Assert.assertTrue
import org.greenstand.android.TreeTracker.utils.PreviewDependencies
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SignUpInstrumentedTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun phone_credential_form() {
        composeTestRule.setContent {
            CompositionLocalProvider(LocalInspectionMode provides true) {
                PreviewDependencies {
                    CredentialEntryView(
                        state =
                            SignUpState(
                                showPrivacyDialog = false,
                            ),
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Phone").assertIsDisplayed()
    }

    @Test
    fun email_credential_form() {
        composeTestRule.setContent {
            CompositionLocalProvider(LocalInspectionMode provides true) {
                PreviewDependencies {
                    CredentialEntryView(
                        state =
                            SignUpState(
                                credential = Credential.Email(),
                                showPrivacyDialog = false,
                            ),
                    )
                }
            }
        }

        composeTestRule.onNodeWithText("Email").assertIsDisplayed()
    }

    @Test
    fun signup_flow_credential_to_name_to_camera() {
        var state by mutableStateOf(
            SignUpState(showPrivacyDialog = false, isCredentialValid = true),
        )
        var cameraRequested = false

        composeTestRule.setContent {
            CompositionLocalProvider(LocalInspectionMode provides true) {
                PreviewDependencies {
                    SignUp(
                        state = state,
                        onHandleAction = { action ->
                            when (action) {
                                SignupAction.SubmitInfo ->
                                    state = state.copy(isCredentialView = false)
                                is SignupAction.UpdateFirstName ->
                                    state = state.copy(firstName = action.firstName)
                                is SignupAction.UpdateLastName ->
                                    state = state.copy(lastName = action.lastName)
                                SignupAction.LaunchCamera ->
                                    cameraRequested = true
                                else -> Unit
                            }
                        },
                        isFormValid = {
                            !state.firstName.isNullOrBlank() && !state.lastName.isNullOrBlank()
                        }
                    )
                }

            }
        }

        composeTestRule.onNodeWithText("Phone").assertIsDisplayed()
        composeTestRule.onNodeWithTag("signup_next").performClick()

        composeTestRule.onNodeWithText("First Name").assertIsDisplayed()
        composeTestRule.onNodeWithText("Last Name").assertIsDisplayed()

        composeTestRule.onNodeWithText("First Name").performTextInput("Ada")
        composeTestRule.onNodeWithText("Last Name").performTextInput("Lovelace")

        composeTestRule.onNodeWithTag("signup_next").performClick()

        assertTrue(cameraRequested)

    }
}