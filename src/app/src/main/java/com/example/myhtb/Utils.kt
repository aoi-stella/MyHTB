package com.example.myhtb

import com.google.gson.JsonParser
import okhttp3.ResponseBody

object Utils {
    /**
     * ResponseBodyクラスの情報から指定したエレメント(element)情報を取り出して返却する
     * @param responseBody ResponseBody情報
     * @param key 取り出したいキー名
     *
     * @return 取り出し結果
     * 取り出し失敗またはエレメント名が無かった場合はnullを返却する
     */
    fun extractSpecifiedValueFromResponseBody(responseBody: ResponseBody, parentKeys: List<String>, key: String): String? {
        val responseBodyStr = responseBody.string()
        var jsonObject = JsonParser().parse(responseBodyStr).asJsonObject
        for (parentKey in parentKeys) {
            jsonObject = jsonObject.get(parentKey)?.asJsonObject ?: return null
        }
        return jsonObject.get(key)?.asString
    }
}