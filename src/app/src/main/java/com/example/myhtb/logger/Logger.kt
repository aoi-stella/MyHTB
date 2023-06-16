package com.example.myhtb.logger

import android.util.Log

object Logger {

    /**
     * ログ情報[レベル：DEBUG]出力処理
     *
     * @param tag タグ名
     * @param message 出力メッセージ
     */
    fun LogDebug(tag: String, message: String){
        Log.d(tag, message)
    }

    /**
     * ログ情報[レベル：INFO]出力処理
     *
     * @param tag タグ名
     * @param message 出力メッセージ
     */
    fun LogInfo(tag: String, message: String){
        Log.i(tag, message)
    }

    /**
     * ログ情報[レベル：WARN]出力処理
     *
     * @param tag タグ名
     * @param message 出力メッセージ
     */
    fun LogWarn(tag: String, message: String){
        Log.w(tag, message)
    }

    /**
     * ログ情報[レベル：ERROR]出力処理
     *
     * @param tag タグ名
     * @param message 出力メッセージ
     */
    fun LogError(tag: String, message: String){
        Log.e(tag, message)
    }
}