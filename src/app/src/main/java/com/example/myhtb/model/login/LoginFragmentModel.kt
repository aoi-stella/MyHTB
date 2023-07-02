package com.example.myhtb.model.login

import com.example.myhtb.logger.Logger
import com.example.myhtb.model.base.repository.HtbRepository

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
     * HackTheBoxへのログイン処理を行う
     *
     * @param email ログイン用メールアドレス
     * @param password ログイン用パスワード
     */
    suspend fun LoginToHackTheBox(email: String, password: String) : Boolean{
        Logger.LogDebug(TAG, "Start LoginToHackTheBox")

        val token = HtbRepository.login(email, password)
        val result =
            if (token != "" && token != null){
                Logger.LogDebug(TAG, "Succeed to fetch access token")
                true
            } else{
                Logger.LogError(TAG, "Failed to fetch access token")
                false
            }
        Logger.LogDebug(TAG, "Finish LoginToHackTheBox")
        return result
    }
}