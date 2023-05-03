package com.example.animal_crossing.ui.customComposables

import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@Composable
fun ProfileHeader(
    image: String,
    containerHeight: Dp
) {
   //val bug = vm
    GlideImage(
        modifier = Modifier
            .heightIn(max = containerHeight / 2)
            .fillMaxHeight(),

        imageModel = { image },
        imageOptions = ImageOptions(contentScale = ContentScale.FillBounds)
    )
}