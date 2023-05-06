package com.example.myhtb.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.myhtb.R
import com.example.myhtb.databinding.FragmentLoginBinding
import com.example.myhtb.viewmodel.login.LoginFragmentViewModel

/**
 * LoginFragmentのViewクラス
 */
class LoginFragmentView : Fragment() {
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
        super.onCreateView(inflater, container, savedInstanceState)

        val binding = DataBindingUtil.inflate<FragmentLoginBinding>(inflater, R.layout.fragment_login, container, false)
        binding.vm = vm
        binding.lifecycleOwner = this
        SetObserve()
        return binding.root
    }

    /**
     * Observeセット
     */
    private fun SetObserve(){
        vm.accessToken.observe(viewLifecycleOwner) { accessToken -> FinishGetToken(accessToken)}
    }

    /**
     * アクセストークン取得時実行処理
     *
     * @param newAccessToken 取得したアクセストークン情報
     */
    private fun FinishGetToken(newAccessToken: String){
        vm.displayProgressIndicator.postValue(false)
        if (newAccessToken.isNotEmpty()) {
            vm.connectionStatus.postValue("Connected")
        } else {
            vm.connectionStatus.postValue("No Connection")
        }
    }

}