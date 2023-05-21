package com.example.myhtb.common

import android.app.Instrumentation
import android.service.autofill.FieldClassification.Match
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.BySelector
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import kotlinx.coroutines.selects.select
import okhttp3.internal.wait
import org.hamcrest.Matchers

object uiUtils {
    private val uiDevice = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

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