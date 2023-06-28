package com.example.myhtb.model.base.repository

import com.example.myhtb.Utils
import com.example.myhtb.model.base.interfaces.HtbService
import com.example.myhtb.logger.Logger
import okhttp3.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

/**
 * ParentKeyを定義するシングルトンデータ
 */
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

    lateinit var AuthToken: String
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
            if (result != null)
                AuthToken = result

            Logger.LogDebug(TAG, "Succeed to fetch access token")
        }
        catch (e: Exception){
            Utils.PrintLogErrorInfo(TAG, e, "Failed to fetch access token")
        }
        Logger.LogDebug(TAG, "Finish Login")
        return result
    }

    /**
     * ユーザーの基本情報を取得する
     *
     * @param authToken 認証トークン
     * @return ユーザーの基本情報(json形式)
     */
    suspend fun GetBasicUserInfo(authToken: String): Response<ResponseBody>? {
        Logger.LogDebug(TAG, "Start GetBasicUserInfo")
        return try {
            val result = service.getBasicUserInfo("Bearer $authToken")
            Logger.LogDebug(TAG, "Code: ${result.code()}")
            result.body()?.string()?.let { Logger.LogDebug(TAG, it) }
            result
        } catch (e: Exception) {
            Utils.PrintLogErrorInfo(TAG, e, "Failed to fetch user info")
            null
        } finally {
            Logger.LogDebug(TAG, "Finish GetBasicUserInfo")
        }
    }
}