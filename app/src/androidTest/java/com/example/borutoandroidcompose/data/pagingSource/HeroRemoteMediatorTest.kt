package com.example.borutoandroidcompose.data.pagingSource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingConfig
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.test.core.app.ApplicationProvider
import com.example.borutoandroidcompose.data.local.BorutoDatabase
import com.example.borutoandroidcompose.data.remote.FakeBorutoApiService
import com.example.borutoandroidcompose.domain.model.Hero
import com.google.common.truth.Truth
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test

class HeroRemoteMediatorTest {

    private lateinit var borutoApiService: FakeBorutoApiService
    private lateinit var borutoDatabase: BorutoDatabase

    @Before
    fun setUp() {
        borutoApiService = FakeBorutoApiService()
        borutoDatabase = BorutoDatabase.createInMemory(
            context = ApplicationProvider.getApplicationContext(),
            allowMainThreadQueries = true
        )
    }

    @After
    fun tearDown() {
        borutoDatabase.clearAllTables()
        borutoDatabase.close()
    }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadReturnsSuccessResultWhenMoreDataIsPresent() =
        runTest {
            val remoteMediator = HeroRemoteMediator(borutoApiService = borutoApiService, borutoDatabase = borutoDatabase)
            val pagingState = PagingState<Int, Hero>(
                pages = emptyList(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0)

            val result = remoteMediator.load(LoadType.REFRESH, pagingState)

            Truth.assertThat(result is RemoteMediator.MediatorResult.Success).isTrue()
            Truth.assertThat((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached).isFalse()
        }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadReturnsSuccessAndEndOfPaginationReachedWhenNoMoreData() =
        runTest {
            borutoApiService.clearData()
            val remoteMediator = HeroRemoteMediator(borutoApiService = borutoApiService, borutoDatabase = borutoDatabase)
            val pagingState = PagingState<Int, Hero>(
                pages = emptyList(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0)

            val result = remoteMediator.load(LoadType.REFRESH, pagingState)

            Truth.assertThat(result is RemoteMediator.MediatorResult.Success).isTrue()
            Truth.assertThat((result as RemoteMediator.MediatorResult.Success).endOfPaginationReached).isTrue()
        }

    @OptIn(ExperimentalPagingApi::class)
    @Test
    fun refreshLoadReturnsErrorWhenErrorOccurs() =
        runTest {
            borutoApiService.setException()
            val remoteMediator = HeroRemoteMediator(borutoApiService = borutoApiService, borutoDatabase = borutoDatabase)
            val pagingState = PagingState<Int, Hero>(
                pages = emptyList(),
                anchorPosition = null,
                config = PagingConfig(pageSize = 3),
                leadingPlaceholderCount = 0)

            val result = remoteMediator.load(LoadType.REFRESH, pagingState)

            Truth.assertThat(result is RemoteMediator.MediatorResult.Error).isTrue()
        }
}