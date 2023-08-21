package com.example.borutoandroidcompose.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
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

    companion object {
        fun createInMemory(context: Context, allowMainThreadQueries: Boolean): BorutoDatabase {
            val database = Room.inMemoryDatabaseBuilder(context = context, BorutoDatabase::class.java)
                .fallbackToDestructiveMigration()

            return if (allowMainThreadQueries) database.allowMainThreadQueries().build()
                else database.build()
        }
    }
    abstract fun heroDao(): HeroDao
    abstract fun heroRemoteKeyDao(): HeroRemoteKeyDao
}