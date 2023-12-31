package com.example.borutoandroidcompose.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<Data>(
    val success: Boolean,
    val message: String? = null,
    val previousPage: Int? = null,
    val nextPage: Int? = null,
    val lastUpdated: Long? = null,
    val result: List<Data> = emptyList()
)
