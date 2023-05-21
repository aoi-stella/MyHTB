package com.example.myhtb.view.login

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.matcher.ViewMatchers
import com.example.myhtb.R

class LoginPageOperations {
    /**
     * Emailアドレス入力機能
     * @param email セットするEmailアドレス
     * @return LoginPageオブジェクト
     */
    fun setEmail(email: String) : LoginPageOperations{
        Espresso.onView(ViewMatchers.withId(R.id.edittext_email)).perform(
            ViewActions.replaceText(email)
        )
        return this
    }

    /**
     * Password入力機能
     * @param password セットするパスワード
     * @return LoginPageオブジェクト
     */
    fun setPassword(password: String) : LoginPageOperations{
        Espresso.onView(ViewMatchers.withId(R.id.edittext_password)).perform(
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
    fun login() : LoginPageOperations{
        //ログインボタン押下
        Espresso.onView(ViewMatchers.withId(R.id.LoginButton))
            .perform(ViewActions.click())
        return this
    }
}