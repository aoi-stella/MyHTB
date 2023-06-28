package com.example.myhtb.viewmodel.userinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhtb.Utils
import com.example.myhtb.logger.Logger
import com.example.myhtb.model.userinfo.UserInfoFragmentModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
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
}

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
     * ユーザー情報画面ViewModel用のScope
     */
    private val scope = CoroutineScope(Dispatchers.Main)

    /**
     * ユーザー基本情報をまとめたデータクラス
     *
     * @param userName ユーザー名
     */
    private data class userBasicInfo(val userName: String)

    /**
     * ユーザー基本情報を取得及び更新する
     *
     * @see
     * 取得可能な情報は、
     * HtbRepository.ktソース内のGetBasicUserInfoメソッドのコメント文を読むこと
     * 本処理失敗時、表示データは前回のままとする
     */
    fun GetBasicUserInfo(){
        Logger.LogDebug(TAG, "Start GetBasicUserInfo")
        scope.launch {
            val result = UserInfoFragmentModel.GetBasicUserInfo()

            if(result == null){
                Logger.LogError(TAG, "Failed to get basic user info")
                return@launch
            }
            var userBasicInfo: userBasicInfo? = createUserInfoByResponseBody(result) ?: return@launch
            updateUserName(userBasicInfo!!.userName)

            Logger.LogDebug(TAG, "Succeed to get basic user info")
        }
        Logger.LogDebug(TAG, "Finish GetBasicUserInfo")
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
        Logger.LogDebug(TAG, "Finish updateUserEmail")
    }

    /**
     * ResponseBodyクラスからuserBasicInfoデータクラスを生成する
     *
     * @param responseBody HTBRepositoryから取得した結果
     *
     * @return userBasicInfoデータクラス(生成に失敗した場合はnullを返却する)
     */
    private fun createUserInfoByResponseBody(responseBody: ResponseBody) : userBasicInfo?{
        Logger.LogDebug(TAG, "Start updateUserEmail")

        val parentKeys: List<String> = listOf(ParentKeys.INFO)
        val name = Utils.extractSpecifiedValueFromResponseBody(responseBody, parentKeys, Elements.NAME) ?: return null

        Logger.LogDebug(TAG, "Finish updateUserEmail")
        return userBasicInfo(name!!)
    }
}