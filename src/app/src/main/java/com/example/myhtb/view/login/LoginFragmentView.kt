package com.halsec.myhtb.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.halsec.myhtb.R
import com.halsec.myhtb.databinding.FragmentLoginBinding
import com.halsec.myhtb.logger.Logger
import com.halsec.myhtb.viewmodel.login.LoginFragmentViewModel

/**
 * LoginFragmentのViewクラス
 */
class LoginFragmentView : Fragment() {
    /**
     * タグ名
     */
    private var TAG = this::class.java.simpleName

    /**
     * LoginFragmentのViewModel
     */
    private val vm : LoginFragmentViewModel by viewModels()

    /**
     * onCreateView
     *
     * @param inflater インフレーター
     * @param container コンテナ情報
     * @param savedInstanceState バンドル情報
     * @return return view情報
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Logger.LogDebug(TAG, "Start onCreateView")
        super.onCreateView(inflater, container, savedInstanceState)

        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
        binding.vm = vm
        binding.lifecycleOwner = this
        Logger.LogDebug(TAG, "Finish onCreateView")
        return binding.root
    }
}