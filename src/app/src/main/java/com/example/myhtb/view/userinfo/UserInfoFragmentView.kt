package com.example.myhtb.view.userinfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myhtb.R
import com.example.myhtb.databinding.FragmentUserInfoBinding
import com.example.myhtb.logger.Logger
import com.example.myhtb.viewmodel.userinfo.UserInfoFragmentViewModel

/**
 * ユーザー情報画面のViewクラス
 */
class UserInfoFragmentView : Fragment() {
    /**
     * タグ名
     */
    private var TAG = this::class.java.simpleName

    /**
     * LoginFragmentのViewModel
     */
    private val vm : UserInfoFragmentViewModel by viewModels()

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
        Logger.LogDebug(TAG, "Start onCreateView")
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = DataBindingUtil.inflate<FragmentUserInfoBinding>(inflater, R.layout.fragment_user_info, container, false)
        binding.vm = vm
        binding.lifecycleOwner = this
        Logger.LogDebug(TAG, "Finish onCreateView")
        return binding.root
    }
}