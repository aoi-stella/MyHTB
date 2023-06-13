package com.example.myhtb

import com.google.gson.JsonParser
import okhttp3.ResponseBody

object Utils {
    /**
     * ResponseBodyクラスの情報から指定したエレメント(element)情報を取り出して返却する
     * @param responseBody ResponseBody情報
     * @param element 取り出したいエレメント名
     *
     * @return 取り出し結果
     * 取り出し失敗またはエレメント名が無かった場合はnullを返却する
     */
    fun extractSpecifiedElementFromResponseBody(responseBody: ResponseBody, element: String): String? {
        val responseBodyStr = responseBody.string()
        val jsonObject = JsonParser().parse(responseBodyStr).asJsonObject
        return jsonObject.get(element)?.asString
    }
}