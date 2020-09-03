package com.prike.tutorship

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented register_info_fragment_old, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under register_info_fragment_old.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.prike.tutorship", appContext.packageName)
    }
}