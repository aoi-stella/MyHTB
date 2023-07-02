package com.example.myhtb.model.userinfo

import com.example.myhtb.logger.Logger
import com.example.myhtb.model.base.repository.HtbRepository

/**
 * UserInfoFragmentModel
 *
 * Modelクラス。
 * 他Modelクラスからのアクセスも考慮してシングルトンこう制とする
 */
object UserInfoFragmentModel {
    //タグ名
    private var TAG = this::class.java.simpleName

    /**
     * ユーザー名をRepositoryから取得する
     *
     * @return ユーザー名
     */
    suspend fun fetchMyUserName(): String{
        Logger.LogDebug(TAG, "Start fetchMyUserName")
        Logger.LogDebug(TAG, "Finish fetchMyUserName")
        return HtbRepository.fetchUserName()
    }

    /**
     * EmailアドレスをRepositoryから取得する
     *
     * @return Emailアドレス
     */
    suspend fun fetchMyEmail(): String{
        Logger.LogDebug(TAG, "Start fetchMyEmail")
        Logger.LogDebug(TAG, "Finish fetchMyEmail")
        return HtbRepository.fetchMyEmail()
    }

    /**
     * プロフィールアイコン画像用エンドポイントを取得する
     *
     * @return プロフィールアイコン画像用エンドポイント
     */
    suspend fun fetchMyProfileIconEP(): String{
        Logger.LogDebug(TAG, "Start fetchMyProfileIconEP")
        Logger.LogDebug(TAG, "Finish fetchMyProfileIconEP")
        return HtbRepository.fetchMyProfileIconEP()
    }

    /**
     * VIP状態を取得する
     *
     * @return VIP状態
     */
    suspend fun fetchMyVIPStatus(): String{
        Logger.LogDebug(TAG, "Start fetchMyVIPStatus")
        Logger.LogDebug(TAG, "Finish fetchMyVIPStatus")
        return HtbRepository.fetchMyVIPStatus()
    }

    /**
     * マシン接続状態を取得する
     *
     * @return マシン接続状態
     */
    suspend fun fetchMyMachineConnectionStatus(): String{
        Logger.LogDebug(TAG, "Start fetchMyMachineConnectionStatus")
        Logger.LogDebug(TAG, "Finish fetchMyMachineConnectionStatus")
        return HtbRepository.fetchMyMachineConnectionStatus()
    }
}