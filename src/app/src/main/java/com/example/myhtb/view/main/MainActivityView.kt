package com.halsec.myhtb.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.halsec.myhtb.R
import com.halsec.myhtb.logger.Logger
import com.google.android.material.navigation.NavigationView

/**
 * MainActivityのViewクラス
 */
class MainActivityView : AppCompatActivity() {
    /**
     * タグ名
     */
    private var TAG = this::class.java.simpleName

    /**
     * DrawerLayoutインスタンス
     */
    private lateinit var drawerLayout: DrawerLayout

    /**
     * onCreate処理
     *
     * ナビゲーションバーのセットなどを行う
     *
     * @param savedInstanceState バンドル情報
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        Logger.LogDebug(TAG, "Start onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.navView)
        val navController = this.findNavController(R.id.navHostFragment)

        NavigationUI.setupWithNavController(navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
        Logger.LogDebug(TAG, "Finish onCreate")
    }

    /**
     * onSupportNavigateUp
     * @return ナビゲーションセットアップが成功したかどうか
     */
    override fun onSupportNavigateUp(): Boolean {
        Logger.LogDebug(TAG, "Start onSupportNavigateUp")
        val navController = this.findNavController(R.id.navHostFragment)
        Logger.LogDebug(TAG, "Finish onSupportNavigateUp")
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

}