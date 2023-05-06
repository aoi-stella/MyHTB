package com.example.myhtb.viewmodel.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhtb.model.login.LoginFragmentModel

/**
 * LoginFragmentのViewModel
 */
class LoginFragmentViewModel : ViewModel(){
    /**
     * ログイン用メールアドレス
     */
    val loginEmail = MutableLiveData<String>()

    /**
     * ログイン用パスワード
     */
    val loginPassword = MutableLiveData<String>()

    /**
     * プログレスインディケーターのVisibility
     */
    val displayProgressIndicator = MutableLiveData(false)

    /**
     * 接続状態(未接続：No Connection / 接続 : Connected)
     */
    val connectionStatus = MutableLiveData("No Connection")

    /**
     * アクセストークン情報プロパティ
     */
    val accessToken: LiveData<String>
        get() = LoginFragmentModel.accessToken

    /**
     * ログイン実行処理
     */
    fun Login() {
        val email:String = loginEmail.value ?: return
        val password:String = loginPassword.value ?: return

        if(email.isBlank() || password.isBlank())
            return

        displayProgressIndicator.value = true
        LoginFragmentModel.LoginToHackTheBox(email, password)
    }
}