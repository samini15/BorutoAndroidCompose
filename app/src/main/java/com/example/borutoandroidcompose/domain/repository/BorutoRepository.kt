package com.example.borutoandroidcompose.domain.repository

import androidx.paging.PagingData
import com.example.borutoandroidcompose.data.local.LocalDataSource
import com.example.borutoandroidcompose.data.prefs.DataStoreOperations
import com.example.borutoandroidcompose.data.remote.RemoteDataSource
import com.example.borutoandroidcompose.domain.model.Hero
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BorutoRepository @Inject constructor(
    private val localDataSource: LocalDataSource<Hero>,
    private val remoteDataSource: RemoteDataSource<Hero>,
    private val dataStore: DataStoreOperations
) /*: BaseRepository<Hero> */{

    // region Onboarding
    suspend fun saveOnboardingState(completed: Boolean) =
        dataStore.saveOnboardingState(completed = completed)

    fun readOnboardingState(): Flow<Boolean> = dataStore.readOnboardingState()
    // endregion Onboarding

    fun getAllData(): Flow<PagingData<Hero>> =
        remoteDataSource.getAllData()

    fun searchHeroes(query: String): Flow<PagingData<Hero>> =
        remoteDataSource.searchData(query = query)

    suspend fun findSelectedHero(id: Int): Hero =
        localDataSource.getSelectedItem(id = id)
}