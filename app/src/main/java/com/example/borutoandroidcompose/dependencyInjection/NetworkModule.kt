package com.example.borutoandroidcompose.dependencyInjection

import com.example.borutoandroidcompose.data.remote.BorutoApiService
import com.example.borutoandroidcompose.utils.Constants
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.MINUTES) // Wait 15 min to read data
            .connectTimeout(15, TimeUnit.MINUTES)
            .build()
    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(Constants.BORUTO_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(MediaType.get("application/json")))
            .build()

    @Provides
    @Singleton
    fun provideBorutoApiService(retrofit: Retrofit) : BorutoApiService =
        retrofit.create(BorutoApiService::class.java)
}