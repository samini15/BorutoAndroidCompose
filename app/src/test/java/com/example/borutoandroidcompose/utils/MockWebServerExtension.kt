package com.example.borutoandroidcompose.utils

import com.example.borutoandroidcompose.domain.model.Hero
import com.google.gson.GsonBuilder
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source

fun MockWebServer.enqueueMockResponseFromFile(fileName: String, responseCode: Int) {
    javaClass.classLoader?.let {
        val inputStream = it.getResourceAsStream(fileName)
        val source = inputStream.source().buffer()
        val body = source.readString(Charsets.UTF_8)
        val mockResponse = MockResponse()
        mockResponse
            .setResponseCode(responseCode)
            .setBody(body)
        this.enqueue(mockResponse)
    }
}

// TODO Does not work -> not properly converted to json
fun MockWebServer.enqueueMockResponseWithBodyAndResponseCode(body: List<Hero>, responseCode: Int) {
    val gson = GsonBuilder().setLenient().create()
    val expectedResponse = MockResponse()
        .setResponseCode(responseCode)
        .setBody(gson.toJson(body))

    this.enqueue(expectedResponse)
}