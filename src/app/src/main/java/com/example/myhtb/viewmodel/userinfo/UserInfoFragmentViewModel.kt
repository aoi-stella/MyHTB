package com.example.myhtb.viewmodel.userinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhtb.Utils
import com.example.myhtb.logger.Logger
import com.example.myhtb.model.userinfo.UserInfoFragmentModel
import kotlinx.coroutines.launch

/**
 * UserInfoFragmentのViewModel
 */
class UserInfoFragmentViewModel : ViewModel() {

    //タグ名
    private var TAG = this::class.java.simpleName

    /**
     * ユーザー名
     */
    val userName = MutableLiveData("Unknown")

    /**
     * Emailアドレス
     */
    val userEmail = MutableLiveData("Unknown")

    /**
     * ユーザーアイコン
     */
    val userIcon = MutableLiveData("")

    /**
     * マシン接続状態
     */
    val machineConnectionStatus = MutableLiveData("No")

    /**
     *  VIPかどうか
     */
    val isVip = MutableLiveData("Normal")

    /**
     * 現在のランク状態
     */
    val currentRank = MutableLiveData("current rank: -")

    /**
     * 次のランク状態
     */
    val nextRank = MutableLiveData("next rank: -")

    /**
     * 現在のランクポイント
     */
    val currentRankPoints = MutableLiveData("-% out of 100%")

    /**
     * 現在のランクポイント
     */
    val currentRankPointsInt = MutableLiveData(0)

    /**
     * ユーザー基本情報を取得及び更新する
     */
    fun updateAllInfo(){
        viewModelScope.launch {
            updateUserName(UserInfoFragmentModel.fetchMyUserName())
            updateUserEmail(UserInfoFragmentModel.fetchMyEmail())
            updateUserIcon(UserInfoFragmentModel.fetchMyProfileIconEP())
            updateVipStatus(UserInfoFragmentModel.fetchMyVIPStatus())
            updateMachineConnectionStatus(UserInfoFragmentModel.fetchMyMachineConnectionStatus())
            updateCurrentRank(UserInfoFragmentModel.fetchMyCurrentRank())
            updateNextRank(UserInfoFragmentModel.fetchMyNextRank())
            updateCurrentRankPoints(UserInfoFragmentModel.fetchMyCurrentRankPoints())
        }

    }

    /**
     * ユーザー名を更新する
     *
     * @param userName ユーザー名
     */
    private fun updateUserName(userName: String){
        Logger.LogDebug(TAG, "Start updateUserName")

        this.userName.value = userName

        Logger.LogDebug(TAG, "Finish updateUserName")
    }

    /**
     * ユーザーのEmailアドレスを更新する
     *
     * @param userEmail Emailアドレス
     */
    private fun updateUserEmail(userEmail: String){
        Logger.LogDebug(TAG, "Start updateUserEmail")

        this.userEmail.value = userEmail

        Logger.LogDebug(TAG, "Finish updateUserEmail")
    }

    /**
     * ユーザーのアイコン画像を更新する
     *
     * @param userIconEndPoint ユーザーアイコン画像用エンドポイント
     */
    private fun updateUserIcon(userIconEndPoint: String){
        Logger.LogDebug(TAG, "Start updateUserIcon")

        userIcon.value = Utils.BASE_URL + userIconEndPoint

        Logger.LogDebug(TAG, "Finish updateUserIcon")
    }

    /**
     * マシン接続状態を更新する
     *
     * @param status マシン接続状態
     */
    private fun updateMachineConnectionStatus(status: String){
        Logger.LogDebug(TAG, "Start updateMachineConnectionStatus")

        if(status == "0")
            machineConnectionStatus.value = "No"
        else
            machineConnectionStatus.value = "Yes"

        Logger.LogDebug(TAG, "Finish updateMachineConnectionStatus")
    }

    /**
     * VIP状態を更新する
     *
     * @param status VIP状態
     */
    private fun updateVipStatus(status: String){
        Logger.LogDebug(TAG, "Start updateVipStatus")

        if(status == "false")
            isVip.value = "Normal"
        else
            isVip.value = "VIP"

        Logger.LogDebug(TAG, "Finish updateVipStatus")
    }

    /**
     * 現在のランク状態を更新する
     *
     * @param currentRank 現在のランク
     */
    private fun updateCurrentRank(currentRank: String){
        Logger.LogDebug(TAG, "Start updateCurrentRank")

        this.currentRank.value= "current rank: $currentRank"

        Logger.LogDebug(TAG, "Finish updateCurrentRank")
    }

    /**
     * 次のランク状態を更新する
     *
     * @param nextRank 次のランク
     */
    private fun updateNextRank(nextRank: String){
        Logger.LogDebug(TAG, "Start updateNextRank")

        this.nextRank.value= "next rank: $nextRank"

        Logger.LogDebug(TAG, "Finish updateNextRank")
    }

    /**
     * 現在のランクポイントを更新する
     *
     * @param currentRankPoints 現在のランクポイント
     */
    private fun updateCurrentRankPoints(currentRankPoints: String){
        Logger.LogDebug(TAG, "Start updateCurrentRankPoints")

        this.currentRankPoints.value= "$currentRankPoints% out of 100%"
        this.currentRankPointsInt.value = currentRankPoints.toDouble().toInt()

        Logger.LogDebug(TAG, "Finish updateCurrentRankPoints")
    }
}