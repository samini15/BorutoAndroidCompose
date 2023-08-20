package com.example.borutoandroidcompose.data.local

import com.example.borutoandroidcompose.domain.model.Hero

class LocalDataSourceImpl(
    private val database: BorutoDatabase
): LocalDataSource<Hero> {

    private val dao = database.heroDao()
    override suspend fun getSelectedItem(id: Int): Hero =
        dao.findSelectedHero(id = id)
}