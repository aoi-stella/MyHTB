package com.example.myhtb.repository

import com.example.myhtb.Utils
import com.example.myhtb.interfaces.HtbService
import com.google.gson.JsonParser
import okhttp3.*
import okio.IOException
import retrofit2.HttpException
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * データ取り出し時の要素名を定義するクラス
 */
private object HtbElement{
    const val ACCESS_TOKEN = "access_token"
}

/**
 * Repositoryクラス
 *
 * 基本的にModelからのみ呼ばれることを想定している
 */
object HtbRepository {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://www.hackthebox.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val service = retrofit.create(HtbService::class.java)

    /**
     * アクセストークン取得処理
     * @param email ログイン用メールアドレス
     * @param password ログイン用パスワード
     *
     * APIを用いてアクセストークン取得を取得する
     */
    fun Login(email: String, password: String): String? {
        var responseBody: ResponseBody? = null
        var result: String? = null

        try {
            responseBody = service.login(email, password, true)
            result = Utils.extractSpecifiedElementFromResponseBody(responseBody, HtbElement.ACCESS_TOKEN)
        }
        catch (e: HttpException){
            //APIリクエストが失敗した場合
            //TODO Logマネージャークラスのようなものを作成して追加しておく
        }
        catch (e: IOException){
            //NWエラー・タイムアウトエラーなどが発生した場合
            //TODO Logマネージャークラスのようなものを作成して追加しておく
        }
        return result
    }
}