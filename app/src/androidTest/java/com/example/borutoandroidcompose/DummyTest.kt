package com.example.borutoandroidcompose

import com.example.borutoandroidcompose.testSetup.BorutoAndroidTest
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Test

@HiltAndroidTest
class DummyTest: BorutoAndroidTest() {
    @Test
    fun dummyTest() {
        println(db.heroDao())
    }
}