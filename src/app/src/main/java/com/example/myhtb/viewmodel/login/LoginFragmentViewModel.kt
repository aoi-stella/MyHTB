package com.example.myhtb.viewmodel.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhtb.model.login.LoginFragmentModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
     * ログイン画面ViewModel用のScope
     */
    private val scope = CoroutineScope(Dispatchers.Main)

    /**
     * ログイン実行処理
     */
    fun Login() {
        val email:String = loginEmail.value ?: return
        val password:String = loginPassword.value ?: return

        if(email.isBlank() || password.isBlank())
            return

        updateIndicatorVisible(true)
        scope.launch {
            val result = LoginFragmentModel.LoginToHackTheBox(email, password)
            updateIndicatorVisible(false)
            updateConnectionStatus(result)
        }
    }

    private fun updateIndicatorVisible(showIndicator: Boolean){
        displayProgressIndicator.value = showIndicator
    }
    private fun updateConnectionStatus(isLoginSuccess: Boolean){
        if (isLoginSuccess){
            connectionStatus.value = "Connected"
        }else{
            connectionStatus.value = "No Connection"
        }
    }
}