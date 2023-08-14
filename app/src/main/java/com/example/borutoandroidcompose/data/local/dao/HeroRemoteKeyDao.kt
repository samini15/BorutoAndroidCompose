package com.example.borutoandroidcompose.data.local.dao

import androidx.room.Query
import com.example.borutoandroidcompose.domain.model.HeroRemoteKey

interface HeroRemoteKeyDao : BaseDao<HeroRemoteKey> {
    @Query("SELECT * FROM hero_remote_key_table WHERE id = :id")
    suspend fun getRemoteKey(id: Int): HeroRemoteKey?

    @Query("DELETE FROM hero_remote_key_table")
    suspend fun deleteAllRemoteKeys()
}