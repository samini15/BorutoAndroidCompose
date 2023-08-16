package com.example.borutoandroidcompose.domain.repository

import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface BaseRepository<T: Any> {
    fun getAllData(): Flow<PagingData<T>>
}