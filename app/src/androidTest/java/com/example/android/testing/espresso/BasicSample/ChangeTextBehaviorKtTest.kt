package com.example.android.testing.espresso.BasicSample

import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ChangeTextBehaviorKtTest {

    @get:Rule
    var activityScenarioRule = activityScenarioRule<MainActivity>()

    /**
     * Test the default text in the TextView (textToBeChanged) when the app first launches.
     */
    @Test
    fun testDefaultTextInMainActivity() {
        val expectedText = InstrumentationRegistry.getInstrumentation()
            .targetContext.getString(R.string.hello_world)

        onView(withId(R.id.show_text_view))
            .check(matches(withText(expectedText)))
    }

    /**
     * Input "123" and check if the TextView in MainActivity is updated.
     */
    @Test
    fun testChangeTextWith123() {
        onView(withId(R.id.editTextUserInput)).perform(typeText("123"), closeSoftKeyboard())
        onView(withId(R.id.changeTextBt)).perform(click())
        onView(withId(R.id.textToBeChanged)).check(matches(withText("123")))
    }


}
