package com.example.myhtb.model.login

import androidx.lifecycle.MutableLiveData
import com.example.myhtb.repository.HtbRepository

object LoginFragmentModel{
    val accessToken = MutableLiveData<String>()

    fun LoginToHackTheBox(email: String, password: String){
       HtbRepository.Getv4Token(email, password){ it?.let { it1 -> accessToken.postValue(it1) } }
    }
}