package com.example.borutoandroidcompose.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.borutoandroidcompose.utils.Constants

@Entity(tableName = Constants.HERO_REMOTE_KEY_DATABASE_TABLE)
data class HeroRemoteKey(
    @PrimaryKey(autoGenerate = false)
    val id: Int,

    val previousKey: Int?,
    val nextKey: Int?
)
