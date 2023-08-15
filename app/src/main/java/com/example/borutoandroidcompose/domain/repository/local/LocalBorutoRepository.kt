package com.example.borutoandroidcompose.domain.repository.local

import com.example.borutoandroidcompose.data.prefs.DataStoreOperations
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class LocalBorutoRepository @Inject constructor(
    private val dataStore: DataStoreOperations
){
    suspend fun saveOnboardingState(completed: Boolean) =
        dataStore.saveOnboardingState(completed = completed)

    fun readOnboardingState(): Flow<Boolean> = dataStore.readOnboardingState()
}