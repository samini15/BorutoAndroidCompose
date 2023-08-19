package com.example.borutoandroidcompose.dependencyInjection

import android.content.Context
import com.example.borutoandroidcompose.data.prefs.DataStoreOperations
import com.example.borutoandroidcompose.data.prefs.DataStoreOperationsImpl
import com.example.borutoandroidcompose.data.remote.RemoteDataSource
import com.example.borutoandroidcompose.domain.model.Hero
import com.example.borutoandroidcompose.domain.repository.BaseRepository
import com.example.borutoandroidcompose.domain.repository.BorutoRepository
import com.example.borutoandroidcompose.domain.useCases.home.GetAllHeroesUseCase
import com.example.borutoandroidcompose.domain.useCases.home.HomeUseCases
import com.example.borutoandroidcompose.domain.useCases.onboarding.OnboardingUseCases
import com.example.borutoandroidcompose.domain.useCases.onboarding.ReadOnboardingStateUseCase
import com.example.borutoandroidcompose.domain.useCases.onboarding.SaveOnboardingUseCase
import com.example.borutoandroidcompose.domain.useCases.search.SearchHeroesUseCase
import com.example.borutoandroidcompose.domain.useCases.search.SearchUseCases
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
    fun provideOnboardingUseCases(repository: BorutoRepository): OnboardingUseCases =
        OnboardingUseCases(
            saveOnboardingUseCase = SaveOnboardingUseCase(repository = repository),
            readOnboardingStateUseCase = ReadOnboardingStateUseCase(repository = repository)
        )

    @Provides
    @Singleton
    fun provideHomeUseCases(repository: BorutoRepository): HomeUseCases =
        HomeUseCases(
            getAllHeroesUseCase = GetAllHeroesUseCase(repository = repository)
        )

    @Provides
    @Singleton
    fun provideSearchUseCases(repository: BorutoRepository): SearchUseCases =
        SearchUseCases(
            searchHeroesUseCase = SearchHeroesUseCase(repository)
        )
}