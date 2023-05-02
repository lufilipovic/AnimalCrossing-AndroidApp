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
import com.example.animal_crossing.data.api.model.VillagerItem
import com.example.animal_crossing.data.api.viewModel.VillagerViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.glide.GlideImage

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VillagerScreen() {
    val vm = VillagerViewModel()

    LaunchedEffect(key1 = Unit, block = {
        vm.getAllVillagers()
    })

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Row { Text(text = "All ACNH Villagers") } }
            )
        },
        content = {
            if(vm.errorMassage.isEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxHeight(),
                    content = {
                        if(vm.villagerList.isEmpty()) {
                            item {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .wrapContentSize(Alignment.Center)
                                )
                            }
                        }

                        items(items = vm.villagerList) { villager ->
                            VillagerCard(villager = villager)
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
fun VillagerCard(villager: VillagerItem) {
    Card(
        shape = MaterialTheme.shapes.medium,
        modifier = Modifier.padding(16.dp)
    ) {
        Box {
            GlideImage(
                imageModel = { villager.imageUrl },
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
                    Text("Name: ${villager.name}")
                }
            }
        }
    }
}