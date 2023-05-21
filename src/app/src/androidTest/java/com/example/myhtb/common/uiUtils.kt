package com.example.myhtb.common

import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.hamcrest.Matchers

/**
 * uiのユニットテストに関する処理をまとめたもの
 * インスタンス化する必要は特にないためシングルトン構成とする
 */
object uiUtils {
    /** uiDevice **/
    private val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    /**
     * 指定のウィジェットが現れるまで待機する。非同期処理などで使用する
     *
     * @param widget 対象のウィジェット
     * @param timeoutMs タイムアウト時間(単位はミリセカンド)
     */
    fun waitForSpecifiedWidget(widget : BySelector, timeoutMs : Long = 10000){
        ViewMatchers.assertThat(
            uiDevice.wait(
                Until.hasObject(widget),
                timeoutMs
            ),
            Matchers.`is`(true)
        )
    }
}