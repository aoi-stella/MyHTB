package com.example.myhtb.model.login

import androidx.lifecycle.MutableLiveData
import com.example.myhtb.repository.HtbRepository

/**
 * LoginFragmentのModel
 *
 * Modelクラス。
 * 他Modelクラスからのアクセスも考慮してシングルトンこう制とする
 */
object LoginFragmentModel{
    /**
     * アクセストークン
     */
    val accessToken = MutableLiveData<String>()

    /**
     * HackTheBoxへのログイン処理を行う
     *
     * @param email ログイン用メールアドレス
     * @param password ログイン用パスワード
     */
    fun LoginToHackTheBox(email: String, password: String){
       HtbRepository.Getv4Token(email, password){ it?.let { it1 -> accessToken.postValue(it1) } }
    }
}