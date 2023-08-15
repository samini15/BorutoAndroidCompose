package com.example.borutoandroidcompose.dependencyInjection

import android.content.Context
import com.example.borutoandroidcompose.data.prefs.DataStoreOperations
import com.example.borutoandroidcompose.data.prefs.DataStoreOperationsImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideDataStoreOperations(
        @ApplicationContext context: Context
    ) : DataStoreOperations =
        DataStoreOperationsImpl(context = context)
}