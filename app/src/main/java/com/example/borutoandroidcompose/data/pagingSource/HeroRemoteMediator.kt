package com.example.borutoandroidcompose.data.pagingSource

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.example.borutoandroidcompose.data.local.BorutoDatabase
import com.example.borutoandroidcompose.data.remote.BorutoApiService
import com.example.borutoandroidcompose.domain.model.Hero
import com.example.borutoandroidcompose.domain.model.HeroRemoteKey
import java.lang.Exception
import javax.inject.Inject

/**
 * Responsible to access remote data and cache them locally
 */
@OptIn(ExperimentalPagingApi::class)
class HeroRemoteMediator @Inject constructor(
    private val borutoApiService: BorutoApiService,
    private val borutoDatabase: BorutoDatabase
): RemoteMediator<Int, Hero>() {

    private val heroDao = borutoDatabase.heroDao()
    private val heroRemoteKeysDao = borutoDatabase.heroRemoteKeyDao()

    override suspend fun load(loadType: LoadType, state: PagingState<Int, Hero>): MediatorResult {
        return try {
            val page = when (loadType) {
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state = state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeysForFirstItem(state)
                    val previousPage = remoteKeys?.previousPage ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    previousPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeysForLastItem(state)
                    val nextPage = remoteKeys?.nextPage ?: return MediatorResult.Success(endOfPaginationReached = remoteKeys != null)
                    nextPage
                }
            }
            val response = borutoApiService.getAllHeroes(page = page)
            if (response.result.isNotEmpty()) {
                // Offline caching
                borutoDatabase.withTransaction {
                    if (loadType == LoadType.REFRESH) {
                        heroDao.deleteAllHeroes()
                        heroRemoteKeysDao.deleteAllRemoteKeys()
                    }
                    val previousPage = response.previousPage
                    val nextPage = response.nextPage
                    val keys = response.result.map { hero ->
                        HeroRemoteKey(
                            id = hero.id,
                            previousPage = previousPage,
                            nextPage = nextPage
                        )
                    }

                    keys.forEach { heroRemoteKeysDao.addEntity(entity = it) }
                    response.result.forEach { heroDao.addEntity(entity = it) }
                }
            }
            MediatorResult.Success(endOfPaginationReached = response.nextPage == null)
        } catch (e: Exception) {
            MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(state: PagingState<Int, Hero>) : HeroRemoteKey? =
        state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                heroRemoteKeysDao.getRemoteKey(id = id)
            }
        }

    private suspend fun getRemoteKeysForFirstItem(state: PagingState<Int, Hero>): HeroRemoteKey? =
        state.pages.firstOrNull {
            it.data.isNotEmpty()
        }?.data?.firstOrNull()?.let { hero ->
            heroRemoteKeysDao.getRemoteKey(id = hero.id)
        }

    private suspend fun getRemoteKeysForLastItem(state: PagingState<Int, Hero>): HeroRemoteKey? =
        state.pages.lastOrNull {
            it.data.isNotEmpty()
        }?.data?.lastOrNull()?.let {  hero ->
            heroRemoteKeysDao.getRemoteKey(id = hero.id)
        }
}