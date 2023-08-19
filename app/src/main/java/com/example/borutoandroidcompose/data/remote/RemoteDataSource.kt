package com.example.borutoandroidcompose.data.remote

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface RemoteDataSource<Data : Any> {
    fun getAllData(): Flow<PagingData<Data>>
    fun searchData(query: String): Flow<PagingData<Data>>
}