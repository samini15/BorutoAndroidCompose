package com.example.borutoandroidcompose.dependencyInjection

import android.content.Context
import com.example.borutoandroidcompose.data.prefs.DataStoreOperations
import com.example.borutoandroidcompose.data.prefs.DataStoreOperationsImpl
import com.example.borutoandroidcompose.domain.repository.local.LocalBorutoRepository
import com.example.borutoandroidcompose.domain.useCases.onboarding.OnboardingUseCases
import com.example.borutoandroidcompose.domain.useCases.onboarding.ReadOnboardingStateUseCase
import com.example.borutoandroidcompose.domain.useCases.onboarding.SaveOnboardingUseCase
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

    @Provides
    @Singleton
    fun provideOnboardingUseCases(repository: LocalBorutoRepository) : OnboardingUseCases =
        OnboardingUseCases(
            saveOnboardingUseCase = SaveOnboardingUseCase(repository = repository),
            readOnboardingStateUseCase = ReadOnboardingStateUseCase(repository = repository)
        )
}