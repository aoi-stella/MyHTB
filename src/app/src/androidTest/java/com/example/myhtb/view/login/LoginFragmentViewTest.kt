package com.example.myhtb.view.login

import android.util.Log
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
class LoginPage {
    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivityView::class.java)

    /**
     * Emailアドレス入力機能
     * @param email セットするEmailアドレス
     * @return LoginPageオブジェクト
     */
    @Test
    fun setEmail(email: String) : LoginPage{
        onView(withId(R.id.edittext_email)).perform(
            ViewActions.replaceText(email)
        )
        return this
    }

    /**
     * Password入力機能
     * @param password セットするパスワード
     * @return LoginPageオブジェクト
     */
    @Test
    fun setPassword(password: String) : LoginPage{
        onView(withId(R.id.edittext_password)).perform(
            ViewActions.replaceText(password)
        )
        return this
    }

    /**
     * ログイン機能
     * @param email セットするEmailアドレス
     * @param password セットするPassword
     * @return LoginPageオブジェクト
     */
    @Test
    fun login(email: String, password: String) : LoginPage{
        //Emailアドレスセット
        setEmail(email)

        //Passwordセット
        setPassword(password)

        //TODO 後で正しいEmail&Passwordの組み合わせ時の期待値を変えとく
        var correctConnectionStatus = "";
        val isSuccess = true
        if(isSuccess)
            correctConnectionStatus = "Connected"
        else
            correctConnectionStatus = "No Connection"

        //ログインボタン押下
        val loginButton = onView(withId(R.id.LoginButton))
            .perform(ViewActions.click())

        //接続実施後のTextViewの状態を確認
        //TODO ログイン処理が終わる前にAssertする可能性があるのでSync?的なのしとく？？
        val connectionStatusTextView = onView(withId(R.id.ConnectionStatusTextView))
            .check(matches(isDisplayed()))
            .check(matches(withText(correctConnectionStatus)))
        return this
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