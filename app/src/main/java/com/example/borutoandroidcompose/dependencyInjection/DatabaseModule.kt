package com.example.borutoandroidcompose.dependencyInjection

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.borutoandroidcompose.data.local.BorutoDatabase
import com.example.borutoandroidcompose.data.local.LocalDataSource
import com.example.borutoandroidcompose.data.local.LocalDataSourceImpl
import com.example.borutoandroidcompose.domain.model.Hero
import com.example.borutoandroidcompose.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : BorutoDatabase = Room.databaseBuilder(
        context,
        BorutoDatabase::class.java,
        Constants.BORUTO_DATABASE
    ).build()

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: BorutoDatabase
    ) : LocalDataSource<Hero> =
        LocalDataSourceImpl(database = database)
}