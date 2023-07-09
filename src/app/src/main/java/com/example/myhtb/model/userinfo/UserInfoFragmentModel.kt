package com.halsec.myhtb.model.userinfo

import com.halsec.myhtb.logger.Logger
import com.halsec.myhtb.model.base.repository.HtbRepository

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

    /**
     * 現在のランクを取得する
     *
     * @return 現在のランク
     */
    suspend fun fetchMyCurrentRank(): String{
        Logger.LogDebug(TAG, "Start fetchMyCurrentRank")
        Logger.LogDebug(TAG, "Finish fetchMyCurrentRank")
        return HtbRepository.fetchMyCurrentRank()
    }

    /**
     * 次のランクを取得する
     *
     * @return 次のランク
     */
    suspend fun fetchMyNextRank(): String{
        Logger.LogDebug(TAG, "Start fetchMyNextRank")
        Logger.LogDebug(TAG, "Finish fetchMyNextRank")
        return HtbRepository.fetchMyNextRank()
    }

    /**
     * 現在のランクポイントを取得する
     *
     * @return 現在のランクポイント
     */
    suspend fun fetchMyCurrentRankPoints(): String{
        Logger.LogDebug(TAG, "Start fetchMyCurrentRankPoints")
        Logger.LogDebug(TAG, "Finish fetchMyCurrentRankPoints")
        return HtbRepository.fetchMyCurrentRankPoints()
    }

    /**
     * 現在のフォロワー数を取得する
     *
     * @return 現在のフォロワー数
     */
    suspend fun fetchMyFollowersCount(): String{
        Logger.LogDebug(TAG, "Start fetchMyFollowersCount")
        Logger.LogDebug(TAG, "Finish fetchMyFollowersCount")
        return HtbRepository.fetchMyFollowersCount()
    }
}