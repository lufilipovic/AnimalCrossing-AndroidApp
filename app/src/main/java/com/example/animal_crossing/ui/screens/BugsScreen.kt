package com.example.animal_crossing.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.example.animal_crossing.data.api.model.BugItem
import com.example.animal_crossing.data.api.model.BugViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BugsScreen() {
    val vm = BugViewModel()

    LaunchedEffect(key1 = Unit, block = {
        vm.getAllBugs()
    })

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Row { Text(text = "All ACNH Bugs") } }
            )
        },
        content = {
            if(vm.errorMassage.isEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(),
                    content = {
                        if(vm.bugList.isEmpty()) {
                            item {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .wrapContentSize(Alignment.Center)
                                )
                            }
                        }

                        items(items = vm.bugList) { bug ->
                            BugImageCard(bug = bug)
                        }
                    }
                )
            } else {
                Text(vm.errorMassage)
            }
        }
    )
}

@Composable
fun BugImageCard(bug: BugItem) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(16.dp)
    ) {
        Box {
            GlideImage(
                imageModel = { bug.imageUrl },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                imageOptions = ImageOptions(contentScale = ContentScale.FillBounds),
                loading = {
                    Box(modifier = Modifier.matchParentSize()) {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                },
                failure = {
                    Text("Image request failed")
                    //println(photograph.url)
                }
            )

            Surface(
                color = MaterialTheme.colors.onSurface.copy(alpha = .3f),
                modifier = Modifier.align(Alignment.BottomCenter),
                contentColor = MaterialTheme.colors.surface
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {
                    Text("Name: ${bug.name}")
                }
            }
        }
    }
}