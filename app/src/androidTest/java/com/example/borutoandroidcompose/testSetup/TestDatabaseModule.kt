package com.example.borutoandroidcompose.testSetup

import android.content.Context
import androidx.room.Room
import com.example.borutoandroidcompose.data.local.BorutoDatabase
import com.example.borutoandroidcompose.data.local.LocalDataSource
import com.example.borutoandroidcompose.data.local.LocalDataSourceImpl
import com.example.borutoandroidcompose.dependencyInjection.DatabaseModule
import com.example.borutoandroidcompose.domain.model.Hero
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [DatabaseModule::class]
)
object TestDatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ) : BorutoDatabase = Room.inMemoryDatabaseBuilder(context, BorutoDatabase::class.java).build()

    @Provides
    @Singleton
    fun provideLocalDataSource(
        database: BorutoDatabase
    ) : LocalDataSource<Hero> =
        LocalDataSourceImpl(database = database)


}