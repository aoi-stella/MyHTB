package com.example.myhtb.repository

import java.util.concurrent.TimeUnit
import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

/**
 * EPのURLを定義するクラス
 */
object HtbEpUrl{
    const val EP_PREFIX = "https://www.hackthebox.com"
    const val EP_LOGIN = "/api/v4/login"
}

/**
 * POST時のタグ名を定義するクラス
 */
object  HtbPostTag{
    const val PT_EMAIL = "email"
    const val PT_PASSWORD = "password"
    const val PT_REMEMBER = "remember"
}

/**
 * データ取り出し時の要素名を定義するクラス
 */
object HtbElement{
    const val ELEMENT_ACCESS_TOKEN = "access_token"
}

/**
 * Repositoryクラス
 *
 * 基本的にModelからのみ呼ばれることを想定している
 */
object HtbRepository {
    /**
     * Media変換情報
     */
    private val JSON_MEDIA = "application/json; charset=utf-8".toMediaType()

    /**
     * クライアント
     * タイムアウト時間は一律5sとする
     */
    private val client = OkHttpClient.Builder()
        .connectTimeout(5, TimeUnit.SECONDS)
        .readTimeout(5, TimeUnit.SECONDS)
        .build()

    /**
     * アクセストークン取得処理
     * @param email ログイン用メールアドレス
     * @param password ログイン用パスワード
     * @param callback アクセストークン取得完了時コールバック処理
     *
     * APIを用いてアクセストークン取得を取得する
     */
    fun Getv4Token(email: String, password: String, callback: (String?) -> Unit) {
        val payload = "{" +
                "\"${HtbPostTag.PT_EMAIL}\":\"$email\"," +
                "\"${HtbPostTag.PT_PASSWORD}\":\"$password\"," +
                "\"${HtbPostTag.PT_REMEMBER}\":\"true\"" +
                "}"
        val request = Request.Builder()
            .url(HtbEpUrl.EP_PREFIX + HtbEpUrl.EP_LOGIN)
            .post(payload.toRequestBody(JSON_MEDIA))
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    callback(extractSpecifiedElementFromResponseBody(response.body!!, HtbElement.ELEMENT_ACCESS_TOKEN))
                } else {
                    callback("")
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e("Error", e.toString())
                callback("")
            }
        })
    }

    /**
     * ResponseBodyクラスの情報から指定したエレメント(element)情報を取り出して返却する
     * @param responseBody ResponseBody情報
     * @param element 取り出したいエレメント名
     *
     * @return 取り出し結果
     * 取り出し失敗またはエレメント名が無かった場合は空文字を返却する
     */
    private fun extractSpecifiedElementFromResponseBody(
        responseBody: ResponseBody,
        element: String
    ): String? {
        //TODO : 何かもうちょいスマートにしたい。ライブラリとか探してparser使うか？？
        //TODO : あとこの機能はHtb依存じゃないからUtilsみたいなの作ってそっちに移動させるべき
        val responseBodyStr = responseBody.string()
        if (responseBodyStr == "")
            return ""

        val pattern = """"$element"\s*:\s*"(.+?)"""".toRegex()
        val matchResult = pattern.find(responseBodyStr ?: "")
        return matchResult?.groups?.get(1)?.value
    }
}