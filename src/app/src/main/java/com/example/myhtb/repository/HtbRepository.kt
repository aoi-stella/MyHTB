package com.example.myhtb.repository

import android.util.Log
import com.example.myhtb.Utils
import com.example.myhtb.interfaces.HtbService
import com.example.myhtb.logger.Logger
import okhttp3.*
import okio.IOException
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

private object ParentKeys{
    const val MESSAGE = "message"
}
/**
 * データ取り出し時の要素名を定義するクラス
 */
private object Elements{
    const val ACCESS_TOKEN = "access_token"
}

/**
 * Repositoryクラス
 *
 * 基本的にModelからのみ呼ばれることを想定している
 */
object HtbRepository {
    /**
     * タグ名
     */
    private var TAG = this::class.java.simpleName

    /**
     * ベースURL
     */
    private const val BASE_URL = "https://www.hackthebox.com/"

    /**
     * Retrofitビルダー
     */
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Retrofitサービス
     */
    private val service = retrofit.create(HtbService::class.java)

    /**
     * アクセストークン取得処理
     * @param email ログイン用メールアドレス
     * @param password ログイン用パスワード
     *
     * APIを用いてアクセストークン取得を取得する
     */
    suspend fun Login(email: String, password: String): String? {
        Logger.LogDebug(TAG, "Start Login")

        val responseBody: ResponseBody
        var result: String? = null

        try {
            val parentKeys: List<String> = listOf(ParentKeys.MESSAGE)
            responseBody = service.login(email, password, true)
            result = Utils.extractSpecifiedValueFromResponseBody(responseBody, parentKeys, Elements.ACCESS_TOKEN)
            Logger.LogDebug(TAG, "Succeed to fetch access token")
        }
        catch (e: Exception){
            val errorType = when (e) {
                is HttpException -> "HTTP error"
                is IOException -> "Network/timeout error"
                else -> "Unknown error"
            }
            Logger.LogError(TAG, "Failed to fetch access token due to $errorType: ${e.message ?: "No error message available"}")
        }
        Logger.LogDebug(TAG, "Finish Login")
        return result
    }
}