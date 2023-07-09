package com.halsec.myhtb.viewmodel.userinfo

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

/**
 * ユーザー情報画面で使用するカスタムバインディングアダプターを定義する
 */
object CustomBindingAdapters {
    /**
     * ShapeableImageView用カスタムバインディングアダプター
     *
     * @param imageView ShapeableImageViewオブジェクト想定
     * @param userIcon ユーザーアイコンのURL
     *
     * @see
     * userIconの中身がNullまたは空文字の場合は何も実行しない
     */
    @JvmStatic
    @BindingAdapter("userIcon")
    fun bindImageURL(imageView: ShapeableImageView, userIcon: String?) {
        if (!userIcon.isNullOrEmpty()) {
            Glide.with(imageView.context)
                .load(userIcon)
                .into(imageView)
        }
    }
}