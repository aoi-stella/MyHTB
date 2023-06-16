package com.example.myhtb.model.login

import com.example.myhtb.logger.Logger
import com.example.myhtb.repository.HtbRepository

/**
 * LoginFragmentのModel
 *
 * Modelクラス。
 * 他Modelクラスからのアクセスも考慮してシングルトンこう制とする
 */
object LoginFragmentModel{
    /**
     * タグ名
     */
    private var TAG = this::class.java.simpleName

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
        Logger.LogDebug(TAG, "Start LoginToHackTheBox")

        var result: Boolean
        val token = HtbRepository.Login(email, password)
        if (token != "" && token != null){
            Logger.LogDebug(TAG, "Succeed to fetch access token")
            _accessToken = token
            result = true
        }
        else{
            Logger.LogDebug(TAG, "Failed to fetch access token")
            _accessToken = ""
            result = false
        }
        Logger.LogDebug(TAG, "Finish LoginToHackTheBox")
        return result
    }
}