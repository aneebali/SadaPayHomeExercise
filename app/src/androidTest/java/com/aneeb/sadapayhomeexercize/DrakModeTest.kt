package com.aneeb.sadapayhomeexercize

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class DrakModeTest {

    @Before
    fun setup() {
        launchActivity<MainActivity>()
    }

    @Test
    fun test() {
        openActionBarOverflowOrOptionsMenu(getInstrumentation().getTargetContext());
        onView(withText("Settings")).perform(ViewActions.click())
        onView(withText("Night mode")).perform(ViewActions.click())
        onView(withText("Dark")).perform(ViewActions.click())
        onView(isRoot()).perform(ViewActions.pressBack());
    }

}