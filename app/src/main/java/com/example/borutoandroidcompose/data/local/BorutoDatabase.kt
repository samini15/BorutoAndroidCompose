package com.example.borutoandroidcompose.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.borutoandroidcompose.data.local.dao.HeroDao
import com.example.borutoandroidcompose.data.local.dao.HeroRemoteKeyDao
import com.example.borutoandroidcompose.domain.model.Hero
import com.example.borutoandroidcompose.domain.model.HeroRemoteKey

@Database(entities = [Hero::class, HeroRemoteKey::class], version = 1)
abstract class BorutoDatabase : RoomDatabase() {
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}