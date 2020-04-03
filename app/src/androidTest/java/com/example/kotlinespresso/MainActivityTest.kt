package com.example.kotlinespresso

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.junit.Assert.*
import org.junit.Test

class MainActivityTest{
    @Test
    fun test_isActivityInView() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.main)).check(matches(isDisplayed())) // is mainActivity displayed
        onView(withId(R.id.activity_main_title)).check(matches(isDisplayed())) // is activity_main_title displayed
        onView(withId(R.id.button_next_activity)).check(matches(withEffectiveVisibility(Visibility.VISIBLE))) // is button_next_activity displayed

        onView(withId(R.id.button_next_activity)).perform(click())  // is button_next_activity clicked
        onView(withId(R.id.secondary)).check(matches(isDisplayed())) //  is secondary activity displayed
    }

    @Test
    fun test_navSecondaryActivity() {
        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.button_next_activity)).perform(click())
        onView(withId(R.id.secondary)).check(matches(isDisplayed()))
    }

    /**
     * Test both ways to navigate from SecondaryActivity to MainActivity
     */
    @Test
    fun test_backPress_toMainActivity() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.button_next_activity)).perform(click())

        onView(withId(R.id.secondary)).check(matches(isDisplayed()))

        onView(withId(R.id.button_back)).perform(click()) // method 1

        onView(withId(R.id.main)).check(matches(isDisplayed()))

        onView(withId(R.id.button_next_activity)).perform(click())

        pressBack() // method 2

        onView(withId(R.id.main)).check(matches(isDisplayed()))
    }
}