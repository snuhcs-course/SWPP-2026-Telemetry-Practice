package com.example.telemetrypractice

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CounterTest {
    @get:Rule val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test fun countAndResetButtonsUpdateDisplay() {
        onView(withId(R.id.tvCount)).check(matches(withText("0")))
        onView(withId(R.id.btnCount)).perform(click(), click(), click())
        onView(withId(R.id.tvCount)).check(matches(withText("3")))
        onView(withId(R.id.btnReset)).perform(click())
        onView(withId(R.id.tvCount)).check(matches(withText("0")))
    }
}
