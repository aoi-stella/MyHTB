package com.example.myhtb.viewmodel.userinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myhtb.Utils
import com.example.myhtb.logger.Logger
import com.example.myhtb.model.userinfo.UserInfoFragmentModel
import kotlinx.coroutines.launch
import okhttp3.ResponseBody

/**
 * ParentKeyを定義するシングルトンデータ
 */
private object ParentKeys{
    const val INFO = "info"
}

/**
 * データ取り出し時の要素名を定義するクラス
 */
private object Elements{
    const val NAME = "name"
    const val EMAIL = "email"
    const val AVATAR = "avatar"
    const val MACHINE_STATUS = "status"
    const val IS_VIP = "isVip"
}

/**
 * UserInfoFragmentのViewModel
 */
class UserInfoFragmentViewModel : ViewModel() {

    /**
     * タグ名
     */
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
     * ユーザー基本情報をまとめたデータクラス
     *
     * @param userName ユーザー名
     * @param userEmail Emailアドレス
     * @param userIconEndPoint ユーザーアイコン用エンドポイントURL
     * @param isVip vip状態かどうか
     */
    private data class userBasicInfo
        (
            val userName: String,
            val userEmail: String,
            val userIconEndPoint: String,
            val isVip: String
        )


    /**
     * ユーザー基本情報を取得及び更新する
     *
     * @see
     * 取得可能な情報は、
     * HtbRepository.ktソース内のGetBasicUserInfoメソッドのコメント文を読むこと
     * 本処理失敗時、表示データは前回のままとする
     */
    fun updateAllInfo(){
        viewModelScope.launch {
            val userInfo = UserInfoFragmentModel.GetBasicUserInfo()
            val machineConnectionInfo = UserInfoFragmentModel.getMachineConnectionStatus()

            if(userInfo == null || machineConnectionInfo == null){
                Logger.LogError(TAG, "Failed to fetch data")
                return@launch
            }

            var userInfoData: userBasicInfo? = createUserInfoByResponseBody(userInfo) ?: return@launch
            val machineConnectionData = Utils.extractSpecifiedValueFromResponseBodyString(machineConnectionInfo.string(), null, Elements.MACHINE_STATUS) ?: return@launch

            updateUserName(userInfoData!!.userName)
            updateUserEmail(userInfoData!!.userEmail)
            updateUserIcon(userInfoData!!.userIconEndPoint)
            updateVipStatus(userInfoData!!.isVip)
            updateMachineConnectionStatus(machineConnectionData)
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

    private fun updateUserIcon(userIconEndPoint: String){
        Logger.LogDebug(TAG, "Start updateUserIcon")

        userIcon.value = Utils.BASE_URL + userIconEndPoint

        Logger.LogDebug(TAG, "Finish updateUserIcon")
    }

    private fun updateMachineConnectionStatus(status: String){
        Logger.LogDebug(TAG, "Start updateMachineConnectionStatus")

        if(status == "0")
            machineConnectionStatus.value = "No"
        else
            machineConnectionStatus.value = "Yes"

        Logger.LogDebug(TAG, "Finish updateMachineConnectionStatus")
    }

    private fun updateVipStatus(status: String){
        Logger.LogDebug(TAG, "Start updateVipStatus")

        if(status == "false")
            isVip.value = "Normal"
        else
            isVip.value = "VIP"

        Logger.LogDebug(TAG, "Finish updateVipStatus")
    }

    /**
     * ResponseBodyクラスからuserBasicInfoデータクラスを生成する
     *
     * @param responseBody HTBRepositoryから取得した結果
     *
     * @return userBasicInfoデータクラス(生成に失敗した場合はnullを返却する)
     */
    private fun createUserInfoByResponseBody(responseBody: ResponseBody) : userBasicInfo?{
        Logger.LogDebug(TAG, "Start createUserInfoByResponseBody")

        val responseBodyString = responseBody.string()
        val parentKeys: List<String> = listOf(ParentKeys.INFO)
        val name = Utils.extractSpecifiedValueFromResponseBodyString(responseBodyString, parentKeys, Elements.NAME) ?: return null
        val email = Utils.extractSpecifiedValueFromResponseBodyString(responseBodyString, parentKeys, Elements.EMAIL) ?: return null
        val iconEndPoint = Utils.extractSpecifiedValueFromResponseBodyString(responseBodyString, parentKeys, Elements.AVATAR) ?: return null
        val isVip = Utils.extractSpecifiedValueFromResponseBodyString(responseBodyString, parentKeys, Elements.IS_VIP) ?: return null


        Logger.LogDebug(TAG, "Finish createUserInfoByResponseBody")
        return userBasicInfo(name, email, iconEndPoint, isVip)
    }
}