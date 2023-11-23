package com.rafiul.composepaging.page

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.rafiul.composepaging.data.models.User
import com.rafiul.composepaging.data.repository.UserRepository
import retrofit2.HttpException
import java.io.IOException

class UserPagingSource(private val repository: UserRepository) : PagingSource<Int, User>() {

    companion object {
        private const val STARTING_PAGE_INDEX = 1
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val position = params.key ?: STARTING_PAGE_INDEX
            val response = repository.getUsers(page = position, limit = 10)
            LoadResult.Page(
                data = response.users,
                prevKey = if (position == STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (response.users.isNotEmpty()) response.page + 1 else null
            )

        } catch (exception: Exception) {
            LoadResult.Error(exception)
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        }
    }


    override fun getRefreshKey(state: PagingState<Int, User>) =
        state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
}