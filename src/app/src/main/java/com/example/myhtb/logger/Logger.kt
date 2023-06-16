package com.example.myhtb.logger

import android.util.Log

object Logger {
    fun LogDebug(tag: String, message: String){
        Log.d(tag, message)
    }

    fun LogInfo(tag: String, message: String){
        Log.i(tag, message)
    }

    fun LogWarn(tag: String, message: String){
        Log.w(tag, message)
    }

    fun LogError(tag: String, message: String){
        Log.e(tag, message)
    }
}