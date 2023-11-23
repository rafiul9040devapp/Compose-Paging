package com.rafiul.composepaging

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.rafiul.composepaging.data.repository.UserRepository
import com.rafiul.composepaging.page.UserPagingSource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(private val repository: UserRepository) : ViewModel() {

    val userPager = Pager(
        PagingConfig(pageSize = 10)
    ) {
        UserPagingSource(repository)
    }.flow
}