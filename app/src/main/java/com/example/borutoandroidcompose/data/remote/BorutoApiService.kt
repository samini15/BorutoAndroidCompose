package com.example.borutoandroidcompose.data.remote

import com.example.borutoandroidcompose.domain.model.ApiResponse
import com.example.borutoandroidcompose.domain.model.Hero
import retrofit2.http.GET
import retrofit2.http.Query

interface BorutoApiService {

    @GET("boruto/heroes")
    suspend fun getAllHeroes(@Query("page") page: Int = 1) : ApiResponse<Hero>

    @GET("boruto/heroes/search")
    suspend fun searchHeroes(@Query("name") name: String) : ApiResponse<Hero>
}