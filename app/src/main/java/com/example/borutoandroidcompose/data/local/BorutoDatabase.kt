package com.example.borutoandroidcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.borutoandroidcompose.data.local.dao.HeroDao
import com.example.borutoandroidcompose.data.local.dao.HeroRemoteKeyDao
import com.example.borutoandroidcompose.data.local.typeConverters.StringTypeConverter
import com.example.borutoandroidcompose.domain.model.Hero
import com.example.borutoandroidcompose.domain.model.HeroRemoteKey

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1)
@TypeConverters(StringTypeConverter::class)
abstract class BorutoDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}