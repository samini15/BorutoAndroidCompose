package com.example.borutoandroidcompose.data.pagingSource

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.borutoandroidcompose.data.remote.BorutoApiService
import com.example.borutoandroidcompose.domain.model.Hero
import java.lang.Exception
import javax.inject.Inject

class SearchHeroesSource @Inject constructor(
    private val borutoApiService: BorutoApiService,
    private val query: String
): PagingSource<Int, Hero>() {
    override fun getRefreshKey(state: PagingState<Int, Hero>): Int? = state.anchorPosition

    /**
     * Perform async request to API and paginate the result if successful
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Hero> {
        return try {
            val apiResponse = borutoApiService.searchHeroes(name = query)
            val result = apiResponse.result
            if (result.isNotEmpty()) {
                LoadResult.Page(
                    data = result,
                    prevKey = apiResponse.previousPage,
                    nextKey = apiResponse.nextPage
                )
            } else {
                LoadResult.Page(
                    data = emptyList(),
                    prevKey = null,
                    nextKey = null
                )
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}