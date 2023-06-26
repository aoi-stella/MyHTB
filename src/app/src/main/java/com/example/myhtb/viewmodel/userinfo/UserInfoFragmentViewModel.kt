package com.example.myhtb.viewmodel.userinfo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myhtb.logger.Logger
import com.example.myhtb.model.userinfo.UserInfoFragmentModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserInfoFragmentViewModel : ViewModel() {
    /**
     * タグ名
     */
    private var TAG = this::class.java.simpleName


    /**
     * ユーザー名
     */
    val userName = MutableLiveData("ddd")

    /**
     * ユーザー情報画面ViewModel用のScope
     */
    private val scope = CoroutineScope(Dispatchers.Main)

    /**
     * ユーザー基本情報を取得及び更新する
     *
     * @see
     * 取得可能な情報は、
     * HtbRepository.ktソース内のGetBasicUserInfoメソッドのコメント文を読むこと
     */
    fun GetBasicUserInfo(){
        Logger.LogDebug(TAG, "Start GetBasicUserInfo")
        scope.launch {
            val result = UserInfoFragmentModel.GetBasicUserInfo()

            //取得失敗時は前回の情報のままとする
            if(result == null){
                Logger.LogError(TAG, "Failed to get basic user info")
                return@launch
            }

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

    }

    /**
     * ユーザーのEmailアドレスを更新する
     *
     * @param userEmail
     */
    private fun updateUserEmail(userEmail: String){

    }
}