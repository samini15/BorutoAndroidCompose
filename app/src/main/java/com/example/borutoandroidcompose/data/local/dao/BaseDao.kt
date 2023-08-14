package com.example.borutoandroidcompose.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface BaseDao<Entity> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEntity(entity: Entity)

    @Delete
    suspend fun deleteEntity(entity: Entity)
}