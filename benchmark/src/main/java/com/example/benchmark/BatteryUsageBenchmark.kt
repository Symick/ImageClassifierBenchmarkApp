package com.example.benchmark

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.benchmark.macro.CompilationMode
import androidx.benchmark.macro.ExperimentalMetricApi
import androidx.benchmark.macro.MacrobenchmarkScope
import androidx.benchmark.macro.PowerCategory
import androidx.benchmark.macro.PowerCategoryDisplayLevel
import androidx.benchmark.macro.PowerMetric
import androidx.benchmark.macro.StartupMode
import androidx.benchmark.macro.StartupTimingMetric
import androidx.benchmark.macro.junit4.MacrobenchmarkRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.uiautomator.By
import androidx.test.uiautomator.Until
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * This is an example startup benchmark.
 *
 * It navigates to the device's home screen, and launches the default activity.
 *
 * Before running this benchmark:
 * 1) switch your app's active build variant in the Studio (affects Studio runs only)
 * 2) add `<profileable android:shell="true" />` to your app's manifest, within the `<application>` tag
 *
 * Run this benchmark from Studio to see startup measurements, and captured system traces
 * for investigating your app's performance.
 */
@RunWith(AndroidJUnit4::class)
class BatteryUsageBenchmark {
    @get:Rule
    val benchmarkRule = MacrobenchmarkRule()

    @Test
    @OptIn(ExperimentalMetricApi::class)
    @RequiresApi(Build.VERSION_CODES.Q)
    fun startup() = benchmarkRule.measureRepeated(
        packageName = "com.example.aibatteryusagebenchmark",
        metrics = listOf(
            PowerMetric(
                PowerMetric.Energy(
                    mapOf(
                        PowerCategory.CPU to PowerCategoryDisplayLevel.TOTAL,
                        PowerCategory.MEMORY to PowerCategoryDisplayLevel.TOTAL,
                        PowerCategory.GPU to PowerCategoryDisplayLevel.TOTAL
                    )
                )
            )
        ),
        iterations = 5,
        startupMode = StartupMode.COLD,
    ) {
        pressHome()
        startActivityAndWait()

        clickButtons()
    }

    private fun MacrobenchmarkScope.clickButtons() {
        // Runs the test for +- 30 minutes
        val startTime = System.currentTimeMillis()
        val thirtyMinutesInMillis = 30 * 60 * 1000
        while(startTime + thirtyMinutesInMillis > System.currentTimeMillis()) {
            device.wait(Until.hasObject(By.text("Select image from gallery")), 5000L)

            device.findObject(By.text("Select image from gallery")).click()

            device.wait(Until.hasObject(By.text("Upload Image")), 5000L)

            Thread.sleep(500L)

            device.findObject(By.text("Upload Image")).click()

            Thread.sleep(2500L)
        }
    }
}