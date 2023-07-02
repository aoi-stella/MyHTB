package com.example.myhtb.model.base.interfaces

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

/**
 * EPを定義
 */
private object EP{
    const val LOGIN = "/api/v4/login"
    const val INFO = "/api/v4/user/info"
    const val CONNECT_STATUS = "/api/v4/user/connection/status"
    const val PROFILE = "/api/v4/user/profile/basic/"
}

interface HtbService {

    /**
     * 引数を基にログインを行い、認証トークンを取得する
     *
     * @param email Emailアドレス
     * @param password パスワード
     * @param remember ログイン状態を保持するかどうか
     *
     * @return 認証トークン
     */
    @FormUrlEncoded
    @POST(EP.LOGIN)
    suspend fun login(
        @Field("email") email: String,
        @Field("password") password: String,
        @Field("remember") remember: Boolean
    ): ResponseBody

    /**
     * ユーザーの基本情報を取得する。
     * 取得可能な情報は以下の通り
     *
     * ・ID
     * ・Name
     * ・Email
     * ・Timezone
     * ・VIPかどうか
     * ・モデレーターかどうか
     * ・BAN履歴の有無
     * ・サーバーID
     * ・アバター画像
     * ・テスターかどうか
     * ・ランクID
     * ・サブスクリプションのタイプ
     */
    @GET(EP.INFO)
    suspend fun getBasicUserInfo(
        @Header("Authorization") authHeader: String
    ): Response<ResponseBody>

    /**
     * ユーザーのマシン接続状態を取得する。
     *
     * 未接続 : "not connected"
     * 接続済 : "connected"
     */
    @GET(EP.CONNECT_STATUS)
    suspend fun getMachineConnectionStatus(
        @Header("Authorization") authHeader: String
    ): Response<ResponseBody>

    /**
     * ユーザーのプロフィールを取得する
     *
     * @param authHeader 認証トークン
     * @param userId ユーザーId
     */
    @GET(EP.PROFILE + "{userId}")
    suspend fun getProfile(
        @Header("Authorization") authHeader: String,
        @Path("userId") userId: String
    ): Response<ResponseBody>
}