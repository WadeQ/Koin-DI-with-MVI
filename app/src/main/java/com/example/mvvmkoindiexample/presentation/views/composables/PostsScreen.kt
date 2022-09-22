package com.example.mvvmkoindiexample.presentation.views.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.mvvmkoindiexample.presentation.viewmodels.PostsViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

@Composable
fun PostsScreen(
    viewModel: PostsViewModel = getViewModel<PostsViewModel>()
){
   val state = viewModel.state.posts
   val scaffoldState = rememberScaffoldState()
   val scope = rememberCoroutineScope()

   Scaffold(
      scaffoldState = scaffoldState
   ) {
       Row(
           modifier = Modifier
               .fillMaxSize(),
           horizontalArrangement = Arrangement.SpaceBetween,
           verticalAlignment = Alignment.CenterVertically
       ) {
           Text(
               text = "Posts",
               style = MaterialTheme.typography.h4
           )
           Spacer(modifier = Modifier.height(16.dp))
           LazyColumn(
               modifier = Modifier.fillMaxSize()
           ){
               items(state.orEmpty()){ posts ->
                   PostItem(
                       posts = posts,
                       modifier = Modifier
                           .fillMaxWidth()
                           .clickable {  }
                   )
               }
           }
       }
   }
}