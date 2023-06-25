package com.example.myhtb.viewmodel.userinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserInfoFragmentViewModel : ViewModel() {
    /**
     * タグ名
     */
    private var TAG = this::class.java.simpleName


    /**
     * ユーザー名
     */
    val userName = MutableLiveData("test")
}