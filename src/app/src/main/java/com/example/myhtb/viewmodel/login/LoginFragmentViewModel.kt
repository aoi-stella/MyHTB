package com.example.myhtb.viewmodel.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhtb.model.login.LoginFragmentModel

class LoginFragmentViewModel : ViewModel(){
    val loginEmail = MutableLiveData<String>()
    val loginPassword = MutableLiveData<String>()
    val displayProgressIndicator = MutableLiveData(false)
    val connectionStatus = MutableLiveData("No Connection")
    val accessToken: LiveData<String>
        get() = LoginFragmentModel.accessToken

    fun Login() {
        val email:String = loginEmail.value ?: return
        val password:String = loginPassword.value ?: return

        if(email.isBlank() || password.isBlank())
            return

        displayProgressIndicator.value = true
        LoginFragmentModel.LoginToHackTheBox(email, password)
    }
}