package com.example.mvvmkoindiexample.presentation.views.composables

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mvvmkoindiexample.domain.pojo.Posts


@Composable
fun PostItem(
    posts: Posts,
    modifier: Modifier = Modifier,
    cornerRadius: Dp = 10.dp
    ){
    Box(modifier = modifier){
        Canvas(modifier = Modifier.matchParentSize() ){

        }
    }
}