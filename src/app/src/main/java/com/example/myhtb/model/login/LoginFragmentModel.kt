package com.halsec.myhtb.model.login

import com.halsec.myhtb.logger.Logger
import com.halsec.myhtb.model.base.repository.HtbRepository

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
        Logger.logDebug(TAG, "Start LoginToHackTheBox")

        val token = HtbRepository.login(email, password)
        val result =
            if (token != "" && token != null){
                Logger.logDebug(TAG, "Succeed to fetch access token")
                true
            } else{
                Logger.logError(TAG, "Failed to fetch access token")
                false
            }
        Logger.logDebug(TAG, "Finish LoginToHackTheBox")
        return result
    }
}