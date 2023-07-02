package com.example.myhtb.model.base.repository

import com.example.myhtb.Utils
import com.example.myhtb.model.base.interfaces.HtbService
import com.example.myhtb.logger.Logger
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception

/**
 * ParentKeyを定義するシングルトンデータ
 */
private object ParentKeys{
    const val MESSAGE = "message"
    const val INFO = "info"
}

/**
 * データ取り出し時の要素名を定義するクラス
 */
private object Elements{
    const val ACCESS_TOKEN = "access_token"
    const val NAME = "name"
    const val EMAIL = "email"
    const val AVATAR = "avatar"
    const val MACHINE_STATUS = "status"
    const val IS_VIP = "isVip"
}

/**
 * Repositoryクラス
 *
 * 基本的にModelからのみ呼ばれることを想定している
 */
object HtbRepository {
    //タグ名
    private var TAG = this::class.java.simpleName

    //Retrofitビルダー
    private val retrofit = Retrofit.Builder()
        .baseUrl(Utils.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    //Retrofitサービス
    private val service = retrofit.create(HtbService::class.java)

    //認証トークン
    private lateinit var authToken: String

    //ログイン画面にてログイン処理が成功しているか
    private var succeedToLoginMyAccount: Boolean = false

    //ユーザー情報画面にてデータ取得を行ったか
    private var fetchedMyProfileInfo: Boolean = false

    //自身のプロフィール情報
    private var myProfileData: UserProfileData = UserProfileData(
        "Unknown",
        "Unknown",
        "",
        "0",
        "0"
    )

    /**
     * ユーザープロフィールデータクラス
     *
     * @param userName ユーザー名
     * @param email Emailアドレス
     * @param iconEP アイコンの画像用エンドポイント
     * @param vipStatus VIP状態
     * @param machineConnectionStatus マシン接続状態
     */
    private data class UserProfileData(
        val userName: String,
        val email: String,
        val iconEP: String,
        val vipStatus: String,
        val machineConnectionStatus: String)

    /**
     * ユーザー名を取得する。
     * ○キャッシュ情報が無い場合
     * 　→新たにデータを取得する
     *
     * ○新規データ取得成功時
     * 　→取得結果を返却する
     *
     * ○新規データ取得失敗時
     * 　→前回の取得結果を保存する
     * 　→前回の取得結果も無い場合は初期値である"Unknown"を返却する
     *
     * @return ユーザー名
     */
    suspend fun fetchUserName(): String{
        val oldUserName = myProfileData.userName

        Logger.LogDebug(TAG, "Start fetchUserName")

        //既にデータを取得済み
        if(fetchedMyProfileInfo){
            Logger.LogDebug(TAG, "Already fetched my profile data")
            return oldUserName
        }

        //アカウントに未ログインの場合
        if(!succeedToLoginMyAccount){
            Logger.LogError(TAG, "Need to login my account")
            return oldUserName
        }

        val result = fetchMyProfileInfo()
        Logger.LogDebug(TAG, "Finish fetchUserName")
        return if(result)
            myProfileData.userName
        else
            oldUserName
    }

    /**
     * ユーザーメールアドレスを取得する。
     * ○キャッシュ情報が無い場合
     * 　→新たにデータを取得する
     *
     * ○新規データ取得成功時
     * 　→取得結果を返却する
     *
     * ○新規データ取得失敗時
     * 　→前回の取得結果を保存する
     * 　→前回の取得結果も無い場合は初期値である"Unknown"を返却する
     *
     * @return ユーザーメールアドレス
     */
    suspend fun fetchMyEmail(): String{
        val oldEmail = myProfileData.email

        Logger.LogDebug(TAG, "Start fetchMyEmail")
        //既にデータを取得済み
        if(fetchedMyProfileInfo){
            Logger.LogDebug(TAG, "Already fetched my profile data")
            return oldEmail
        }

        //アカウントに未ログインの場合
        if(!succeedToLoginMyAccount){
            Logger.LogError(TAG, "Need to login my account")
            return oldEmail
        }

        val result = fetchMyProfileInfo()
        Logger.LogDebug(TAG, "Finish fetchMyEmail")
        return if(result)
            myProfileData.email
        else
            oldEmail
    }

    /**
     * ユーザーアイコン画像用エンドポイントを取得する。
     * ○キャッシュ情報が無い場合
     * 　→新たにデータを取得する
     *
     * ○新規データ取得成功時
     * 　→取得結果を返却する
     *
     * ○新規データ取得失敗時
     * 　→前回の取得結果を保存する
     * 　→前回の取得結果も無い場合は空文字を返却する
     *
     * @return ユーザーアイコン画像用エンドポイント
     */
    suspend fun fetchMyProfileIconEP(): String{
        val oldIconEP = myProfileData.iconEP

        Logger.LogDebug(TAG, "Start fetchMyProfileIconEP")
        //既にデータを取得済み
        if(fetchedMyProfileInfo){
            Logger.LogDebug(TAG, "Already fetched my profile data")
            return oldIconEP
        }

        //アカウントに未ログインの場合
        if(!succeedToLoginMyAccount){
            Logger.LogError(TAG, "Need to login my account")
            return oldIconEP
        }

        val result = fetchMyProfileInfo()
        Logger.LogDebug(TAG, "Finish fetchMyProfileIconEP")
        return if(result)
            myProfileData.iconEP
        else
            oldIconEP
    }

    /**
     * ユーザーのVIP状態を取得する。
     * ○キャッシュ情報が無い場合
     * 　→新たにデータを取得する
     *
     * ○新規データ取得成功時
     * 　→取得結果を返却する
     *
     * ○新規データ取得失敗時
     * 　→前回の取得結果を保存する
     * 　→前回の取得結果も無い場合は初期値である"false"を返却する
     *　　※"false"はBoolean型ではなくString型で返却されることに注意する
     *
     * @return ユーザーのVIP状態
     */
    suspend fun fetchMyVIPStatus(): String{
        val oldIsVIP = myProfileData.vipStatus

        Logger.LogDebug(TAG, "Start fetchMyProfileIconEP")
        //既にデータを取得済み
        if(fetchedMyProfileInfo){
            Logger.LogDebug(TAG, "Already fetched my profile data")
            return oldIsVIP
        }

        //アカウントに未ログインの場合
        if(!succeedToLoginMyAccount){
            Logger.LogError(TAG, "Need to login my account")
            return oldIsVIP
        }

        val result = fetchMyProfileInfo()
        Logger.LogDebug(TAG, "Finish fetchMyProfileIconEP")
        return if(result)
            myProfileData.vipStatus
        else
            oldIsVIP
    }

    /**
     * ユーザーマシン接続状態を取得する。
     * ○キャッシュ情報が無い場合
     * 　→新たにデータを取得する
     *
     * ○新規データ取得成功時
     * 　→取得結果を返却する
     *
     * ○新規データ取得失敗時
     * 　→前回の取得結果を保存する
     * 　→前回の取得結果も無い場合は初期値である"0"を返却する
     *
     * @return ユーザーマシン接続状態
     */
    suspend fun fetchMyMachineConnectionStatus(): String{
        val oldMachineConnectionStatus = myProfileData.machineConnectionStatus

        Logger.LogDebug(TAG, "Start fetchMyMachineConnectionStatus")
        //既にデータを取得済み
        if(fetchedMyProfileInfo){
            Logger.LogDebug(TAG, "Already fetched my profile data")
            return oldMachineConnectionStatus
        }

        //アカウントに未ログインの場合
        if(!succeedToLoginMyAccount){
            Logger.LogError(TAG, "Need to login my account")
            return oldMachineConnectionStatus
        }

        val result = fetchMyProfileInfo()
        Logger.LogDebug(TAG, "Finish fetchMyMachineConnectionStatus")
        return if(result)
            myProfileData.machineConnectionStatus
        else
            oldMachineConnectionStatus
    }

    /**
     * ユーザープロフィール状態を新規取得する。
     *
     * @return 取得に成功したかどうか(true...成功 / false...失敗)
     */
    private suspend fun fetchMyProfileInfo(): Boolean{
        Logger.LogDebug(TAG, "Start fetchMyProfileInfo")
        return try {
            myProfileData = createProfileData()
            fetchedMyProfileInfo = true
            true
        } catch (e: Exception) {
            Utils.PrintLogErrorInfo(TAG, e, "Failed to fetch my profile")
            fetchedMyProfileInfo = false
            false
        } finally {
            Logger.LogDebug(TAG, "Finish fetchMyProfileInfo")
        }
    }

    /**
     * ユーザーデータを生成する
     *
     * @return 生成されたユーザーデータ
     */
    private suspend fun createProfileData(): UserProfileData{
        Logger.LogDebug(TAG, "Start createProfileData")

        val userProfileResponse = service.getBasicUserInfo("Bearer $authToken")
        val userMachineResponse = service.getMachineConnectionStatus("Bearer $authToken")

        val parentKeys: List<String> = listOf(ParentKeys.INFO)
        val oldProfileData = myProfileData

        if(!userProfileResponse.isSuccessful || !userMachineResponse.isSuccessful)
            return oldProfileData

        val userResponseBodyString = userProfileResponse.body()?.string() ?: return oldProfileData
        val machineResponseBodyString = userMachineResponse.body()?.string() ?: return oldProfileData

        val name = Utils.extractSpecifiedValueFromResponseBodyString(userResponseBodyString, parentKeys, Elements.NAME) ?: return oldProfileData
        val email = Utils.extractSpecifiedValueFromResponseBodyString(userResponseBodyString, parentKeys, Elements.EMAIL) ?: return oldProfileData
        val iconEP = Utils.extractSpecifiedValueFromResponseBodyString(userResponseBodyString, parentKeys, Elements.AVATAR) ?: return oldProfileData
        val vipStatus = Utils.extractSpecifiedValueFromResponseBodyString(userResponseBodyString, parentKeys, Elements.IS_VIP) ?: return oldProfileData
        val machineConnectionStatus = Utils.extractSpecifiedValueFromResponseBodyString(machineResponseBodyString, null, Elements.MACHINE_STATUS) ?: return oldProfileData

        Logger.LogDebug(TAG, "Finish createProfileData")
        return UserProfileData(name, email, iconEP, vipStatus, machineConnectionStatus)
    }

    /**
     * アクセストークン取得処理
     * @param email ログイン用メールアドレス
     * @param password ログイン用パスワード
     *
     * APIを用いてアクセストークン取得を取得する
     */
    suspend fun login(email: String, password: String): String? {
        Logger.LogDebug(TAG, "Start login")

        val responseBody: ResponseBody
        var result: String? = null

        try {
            val parentKeys: List<String> = listOf(ParentKeys.MESSAGE)
            responseBody = service.login(email, password, true)
            result = Utils.extractSpecifiedValueFromResponseBody(responseBody, parentKeys, Elements.ACCESS_TOKEN)
            if (result != null)
                authToken = result

            succeedToLoginMyAccount = true
            Logger.LogDebug(TAG, "Succeed to fetch access token")
        }
        catch (e: Exception){
            Utils.PrintLogErrorInfo(TAG, e, "Failed to fetch access token")
        }
        Logger.LogDebug(TAG, "Finish login")
        return result
    }
}