package com.example.myhtb.view.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.myhtb.R
import com.google.android.material.navigation.NavigationView

/**
 * MainActivityのViewクラス
 */
class MainActivityView : AppCompatActivity() {

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
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawerLayout = findViewById(R.id.drawerLayout)
        val navView: NavigationView = findViewById(R.id.navView)
        val navController = this.findNavController(R.id.navHostFragment)

        NavigationUI.setupWithNavController(navView, navController)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)

    }

    /**
     * onSupportNavigateUp
     * @return ナビゲーションセットアップが成功したかどうか
     */
    override fun onSupportNavigateUp(): Boolean {
        val navController = this.findNavController(R.id.navHostFragment)
        return NavigationUI.navigateUp(navController, drawerLayout)
    }

}