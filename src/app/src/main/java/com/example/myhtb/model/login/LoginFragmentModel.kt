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
    var accessToken: String = ""

    /**
     * HackTheBoxへのログイン処理を行う
     *
     * @param email ログイン用メールアドレス
     * @param password ログイン用パスワード
     */
    suspend fun LoginToHackTheBox(email: String, password: String) : Boolean{
        val token = HtbRepository.Login(email, password)
        if (token != "" && token != null){
            accessToken = token
            return true
        }
        else{
            accessToken = ""
            return false
        }
    }
}