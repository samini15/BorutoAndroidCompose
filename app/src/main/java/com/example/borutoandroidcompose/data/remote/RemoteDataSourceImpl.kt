package com.example.borutoandroidcompose.data.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.borutoandroidcompose.data.local.BorutoDatabase
import com.example.borutoandroidcompose.data.pagingSource.HeroRemoteMediator
import com.example.borutoandroidcompose.data.pagingSource.SearchHeroesSource
import com.example.borutoandroidcompose.domain.model.Hero
import com.example.borutoandroidcompose.utils.Constants
import kotlinx.coroutines.flow.Flow

class RemoteDataSourceImpl(
    private val borutoApiService: BorutoApiService,
    private val borutoDatabase: BorutoDatabase
)  : RemoteDataSource<Hero> {

    private val heroDao = borutoDatabase.heroDao()
    @OptIn(ExperimentalPagingApi::class)
    override fun getAllData(): Flow<PagingData<Hero>> =
        Pager(
            config = PagingConfig(pageSize = Constants.PAGING_BATCH_SIZE),
            remoteMediator = HeroRemoteMediator(
                borutoApiService = borutoApiService,
                borutoDatabase = borutoDatabase
            ),
            pagingSourceFactory = { heroDao.getAllHeroes() }
        ).flow

    override fun searchData(query: String): Flow<PagingData<Hero>> =
        Pager(
            config = PagingConfig(pageSize = Constants.PAGING_BATCH_SIZE),
            pagingSourceFactory = { SearchHeroesSource(borutoApiService = borutoApiService, query = query) }
        ).flow
}