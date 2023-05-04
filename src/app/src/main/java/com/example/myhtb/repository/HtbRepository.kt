package com.example.myhtb.repository

import android.util.Log
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.IOException

object HtbEpUrl{
    const val EP_PREFIX = "https://www.hackthebox.com"
    const val EP_LOGIN = "/api/v4/login"
}

object  HtbPostTag{
    const val PT_EMAIL = "email"
    const val PT_PASSWORD = "password"
    const val PT_REMEMBER = "remember"
}

object HtbElement{
    const val ELEMENT_ACCESS_TOKEN = "access_token"
}

object HtbRepository {
    private val JSON_MEDIA = "application/json; charset=utf-8".toMediaType()

    private val client = OkHttpClient.Builder()
        .build()

    fun Getv4Token(email: String, password: String) : String? {
        var token: String? = ""
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
                if(response.body == null)
                    return
                token = extractSpecifiedElementFromResponseBody(response.body!!, HtbElement.ELEMENT_ACCESS_TOKEN)
            }

            override fun onFailure(call: Call, e: IOException) {
                Log.e("Error", e.toString())
            }
        })

        return token
    }

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