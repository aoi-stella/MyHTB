package com.example.myhtb.view.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.myhtb.R

/**
 * ユーザー情報画面のViewクラス
 */
class UserInfoFragment : Fragment() {

    /**
     * onCreateView処理
     * @param inflater インフレーター
     * @param container コンテナ
     * @param savedInstanceState バンドル情報
     * @return View情報
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_info, container, false)
    }
}