package com.example.borutoandroidcompose.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import com.example.borutoandroidcompose.domain.model.Hero

@Dao
interface HeroDao : BaseDao<Hero> {
    @Query("SELECT * FROM hero_table ORDER BY id ASC")
    fun getAllHeroes() : PagingSource<Int, Hero>

    @Query("SELECT * FROM hero_table WHERE id = :id")
    fun findSelectedHero(id: Int): Hero

    @Query("DELETE FROM hero_table")
    suspend fun deleteAllHeroes()
}