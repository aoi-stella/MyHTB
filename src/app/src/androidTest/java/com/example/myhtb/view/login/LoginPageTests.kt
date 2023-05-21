package com.example.myhtb.view.login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.By
import com.example.myhtb.R
import com.example.myhtb.common.uiUtils
import com.example.myhtb.view.main.MainActivityView
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginPageTests {


    @get:Rule
    var mActivityTestRule = ActivityTestRule(MainActivityView::class.java)

    /**
     * 不正メールアドレスセットによるログイン失敗
     */
    @Test
    fun loginFailedIncorrectEmail(){
        val loginPageOperation = LoginPageOperations()
        loginPageOperation
            .setEmail("test@gmail.com")
            .setPassword("xxxx")
            .login()

        onView(withId(R.id.ConnectionStatusTextView))
            .check(matches(isDisplayed()))
            .check(matches(withText("No Connection")))
    }

    /**
     * 不正パスワードセットによるログイン失敗
     */
    @Test
    fun loginFailedIncorrectPassword(){
        val loginPageOperation = LoginPageOperations()
        loginPageOperation
            .setEmail("xxxx")
            .setPassword("hogehoge")
            .login()

        onView(withId(R.id.ConnectionStatusTextView))
            .check(matches(isDisplayed()))
            .check(matches(withText("No Connection")))
    }

    /**
     * ログイン成功
     */
    @Test
    fun loginSuccessful(){
        val loginPageOperation = LoginPageOperations()
        loginPageOperation
            .setEmail("")
            .setPassword("")
            .login()

        uiUtils.waitForSpecifiedWidget(By.text("Connected"))
    }
}