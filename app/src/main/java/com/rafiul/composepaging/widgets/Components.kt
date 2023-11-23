package com.rafiul.composepaging.widgets


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.request.ImageRequest
import com.rafiul.composepaging.UserViewModel
import com.rafiul.composepaging.data.models.User
import coil.compose.AsyncImage as AsyncImage


@Composable
fun UserList(viewModel: UserViewModel) {
    val userList = viewModel.userPager.collectAsLazyPagingItems()

    when {
        userList.loadState.refresh is LoadState.Loading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        userList.itemCount > 0 -> {
//            LazyColumn {
//                items(items = userList.itemSnapshotList) { user ->
//                    if (user != null) {
//                        UserCard(user = user)
//                    } else {
//                        Text(text = "THERE IS NO USER")
//                    }
//                }
//            }

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(150.dp),
                verticalItemSpacing = 4.dp,
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                content = {
                    items(items = userList.itemSnapshotList) { user ->
                        user?.let {

                            Column {
                                AsyncImage(
                                    model = it.picture,
                                    contentScale = ContentScale.Crop,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight()
                                )

                                Text(
                                    text = it.name,
                                    fontSize = 24.sp,
                                    modifier = Modifier
                                        .padding(start = 12.dp)
                                )
                            }

                        }

                    }
                },
                modifier = Modifier.fillMaxSize()
            )



            when (userList.loadState.append) {
                is LoadState.Loading -> {
                    LoadingItems()
                }

                is LoadState.Error -> {

                }

                else -> {}
            }

        }

        userList.loadState.refresh is LoadState.Error -> {

        }

    }

}

@Composable
fun UserCard(user: User) {
    Card(
        modifier = Modifier
            .padding(6.dp)
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(user.picture)
                    .crossfade(true)
                    .build(),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(shape = CircleShape)
                    .width(width = 42.dp)
                    .height(height = 42.dp)
            )
            Text(
                text = user.name,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(start = 12.dp)
            )
        }
    }
}

@Composable
fun LoadingItems() {
    Box(
        modifier = Modifier
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .width(42.dp)
                .height(42.dp),
            strokeWidth = 5.dp
        )
    }
}