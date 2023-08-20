package com.example.borutoandroidcompose.data.local

interface LocalDataSource<Entity> {
    suspend fun getSelectedItem(id: Int): Entity
}