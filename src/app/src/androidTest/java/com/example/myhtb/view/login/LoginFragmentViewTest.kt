package com.example.myhtb.view.login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.example.myhtb.R
import com.example.myhtb.view.main.MainActivityView
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
     *
     * 本テストは事前にEmail及びPasswordがセットされていることが前提である。
     * そのため、必ずテスト関数 : setEmail() & テスト関数 : setPassword()をコールすること
     *
     * @return LoginPageオブジェクト
     */
    @Test
    fun login() : LoginPage{
        //TODO 後で正しいEmail&Passwordの組み合わせ時の期待値を変えとく
        var correctConnectionStatus = "";
        val isSuccess = true
        if(isSuccess)
            correctConnectionStatus = "Connected"
        else
            correctConnectionStatus = "No Connection"

        //ログインボタン押下
        onView(withId(R.id.LoginButton))
            .perform(ViewActions.click())

        //接続実施後のConnectionStatusTextViewの状態を確認
        //TODO ログイン処理が終わる前にAssertする可能性があるのでSync?的なのしとく？？
        onView(withId(R.id.ConnectionStatusTextView))
            .check(matches(isDisplayed()))
            .check(matches(withText(correctConnectionStatus)))
        return this
    }
}