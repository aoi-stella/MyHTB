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
     * ユーザー基本情報を取得及び更新する
     */
    fun updateAllInfo(){
        viewModelScope.launch {
            updateUserName(UserInfoFragmentModel.fetchMyUserName())
            updateUserEmail(UserInfoFragmentModel.fetchMyEmail())
            updateUserIcon(UserInfoFragmentModel.fetchMyProfileIconEP())
            updateVipStatus(UserInfoFragmentModel.fetchMyVIPStatus())
            updateMachineConnectionStatus(UserInfoFragmentModel.fetchMyMachineConnectionStatus())
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
}