package com.example.myhtb.model.login

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
    private var _accessToken: String = ""
    val accessToken: String
        get() = _accessToken

    /**
     * HackTheBoxへのログイン処理を行う
     *
     * @param email ログイン用メールアドレス
     * @param password ログイン用パスワード
     */
    suspend fun LoginToHackTheBox(email: String, password: String) : Boolean{
        val token = HtbRepository.Login(email, password)
        if (token != "" && token != null){
            _accessToken = token
            return true
        }
        else{
            _accessToken = ""
            return false
        }
    }
}