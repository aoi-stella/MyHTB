package com.example.myhtb.interfaces

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * EPを定義
 */
private object EP{
    const val LOGIN = "/api/v4/login"
}

interface HtbService {
    @FormUrlEncoded
    @POST(EP.LOGIN)
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("remember") remember: Boolean
    ): ResponseBody
}