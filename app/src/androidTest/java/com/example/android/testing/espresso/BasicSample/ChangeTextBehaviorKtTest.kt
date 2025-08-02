package com.example.android.testing.espresso.BasicSample

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import android.view.View
import androidx.test.ext.junit.rules.activityScenarioRule
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


    @Test
    fun testChangeTextWith123() {
        onView(withId(R.id.editTextUserInput)).perform(typeText("123"), closeSoftKeyboard())
        onView(withId(R.id.changeTextBt)).perform(click())
        onView(withId(R.id.textToBeChanged)).check(matches(withText("123")))
    }

    @Test
    fun testOpenActivityWith123() {
        onView(withId(R.id.editTextUserInput)).perform(typeText("123"), closeSoftKeyboard())
        onView(withId(R.id.activityChangeTextBtn)).perform(click())
        onView(isRoot()).perform(waitFor(500)) // Wait for activity transition
        onView(withId(R.id.show_text_view)).check(matches(withText("123")))
    }

    @Test
    fun testChangeTextWithEmpty() {
        onView(withId(R.id.editTextUserInput)).perform(clearText(), closeSoftKeyboard())
        onView(withId(R.id.changeTextBt)).perform(click())
        onView(withId(R.id.textToBeChanged)).check(matches(withText("")))
    }

    @Test
    fun testOpenActivityWithEmpty() {
        onView(withId(R.id.editTextUserInput)).perform(clearText(), closeSoftKeyboard())
        onView(withId(R.id.activityChangeTextBtn)).perform(click())
        onView(isRoot()).perform(waitFor(500))
        onView(withId(R.id.show_text_view)).check(matches(withText("")))
    }

    @Test
    fun testChangeTextWithAbcdef() {
        onView(withId(R.id.editTextUserInput)).perform(typeText("abcdef"), closeSoftKeyboard())
        onView(withId(R.id.changeTextBt)).perform(click())
        onView(withId(R.id.textToBeChanged)).check(matches(withText("abcdef")))
    }

    @Test
    fun testOpenActivityWithAbcdef() {
        onView(withId(R.id.editTextUserInput)).perform(typeText("abcdef"), closeSoftKeyboard())
        onView(withId(R.id.activityChangeTextBtn)).perform(click())
        onView(isRoot()).perform(waitFor(500))
        onView(withId(R.id.show_text_view)).check(matches(withText("abcdef")))
    }

    // Optional: replaces Thread.sleep
    private fun waitFor(delay: Long): ViewAction {
        return object : ViewAction {
            override fun getConstraints() = isRoot()
            override fun getDescription() = "Wait for $delay milliseconds"
            override fun perform(uiController: UiController, view: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }
        }
    }
}