package com.example.borutoandroidcompose.testSetup

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.test.core.app.ApplicationProvider
import com.example.borutoandroidcompose.data.local.BorutoDatabase
import com.example.borutoandroidcompose.data.prefs.dataStore
import dagger.hilt.android.testing.HiltAndroidRule
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import javax.inject.Inject

abstract class BorutoAndroidTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    protected lateinit var context: Context

    @Inject
    lateinit var db: BorutoDatabase

    @Before
    open fun setUp() {
        context = ApplicationProvider.getApplicationContext()
        hiltRule.inject()
        db.clearAllTables()
        clearDataStore()
    }

    @After
    open fun tearDown() {
        db.close()
    }

    private fun clearDataStore() = runBlocking {
        context.dataStore.edit {
            it.clear()
        }
    }
}