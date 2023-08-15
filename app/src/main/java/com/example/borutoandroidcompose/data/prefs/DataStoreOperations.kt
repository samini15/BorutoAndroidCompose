package com.example.borutoandroidcompose.data.prefs

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

interface DataStoreOperations {
    suspend fun saveOnboardingState(completed: Boolean)
    fun readOnboardingState(): Flow<Boolean>
}