package com.halsec.myhtb.logger

import timber.log.Timber

object Logger {
    /** 初期化済みかどうか*/
    private var isInitialized: Boolean = false;

    fun LogInit(){
        Timber.plant()
        isInitialized = true
    }

    /**
     * ログ情報[レベル：DEBUG]出力処理
     *
     * @param tag タグ名
     * @param message 出力メッセージ
     */
    fun LogDebug(tag: String, message: String){
        if(!isInitialized)
            return

        Timber.d(tag, message)
    }

    /**
     * ログ情報[レベル：INFO]出力処理
     *
     * @param tag タグ名
     * @param message 出力メッセージ
     */
    fun LogInfo(tag: String, message: String){
        if(!isInitialized)
            return

        Timber.i(tag, message)
    }

    /**
     * ログ情報[レベル：WARN]出力処理
     *
     * @param tag タグ名
     * @param message 出力メッセージ
     */
    fun LogWarn(tag: String, message: String){
        if(!isInitialized)
            return

        Timber.w(tag, message)
    }

    /**
     * ログ情報[レベル：ERROR]出力処理
     *
     * @param tag タグ名
     * @param message 出力メッセージ
     */
    fun LogError(tag: String, message: String){
        if(!isInitialized)
            return

        Timber.e(tag, message)
    }
}