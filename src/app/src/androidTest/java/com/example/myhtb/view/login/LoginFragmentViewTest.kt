package com.example.myhtb.view.login

import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import com.example.myhtb.R
import com.example.myhtb.view.main.MainActivityView
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginFragmentViewTest {
    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivityView::class.java)

    @Test
    fun loginTest() {
        val textInputEditText = onView(
            Matchers.allOf(
                withId(R.id.edittext_email),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navHostFragment),
                        0
                    ),
                    1
                ),
                ViewMatchers.isDisplayed()
            )
        )
        textInputEditText.perform(
            ViewActions.replaceText("halcorder"),
            ViewActions.closeSoftKeyboard()
        )

        val textInputEditText2 = onView(
            Matchers.allOf(
                withId(R.id.edittext_password),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navHostFragment),
                        0
                    ),
                    2
                ),
                ViewMatchers.isDisplayed()
            )
        )
        textInputEditText2.perform(
            ViewActions.replaceText("kinoko97577"),
            ViewActions.closeSoftKeyboard()
        )

        val materialButton = onView(
            Matchers.allOf(
                withId(R.id.LoginButton), ViewMatchers.withText("Login"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.navHostFragment),
                        0
                    ),
                    3
                ),
                ViewMatchers.isDisplayed()
            )
        )
        materialButton.perform(ViewActions.click())

        onView(Matchers.allOf(withId(R.id.ConnectionStatusTextView)))
            .check(matches(isDisplayed()))
            .check(matches(withText("No Connection")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}