package com.example.animal_crossing.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.animal_crossing.data.api.viewModel.VillagerViewModel
import com.example.animal_crossing.ui.customComposables.CustomImageCard
import com.example.animal_crossing.ui.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun VillagerScreen(navigationController: NavHostController, vm: VillagerViewModel) {
//    val vm = VillagerViewModel()

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
            if (vm.errorMassage.isEmpty()) {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // Display 2 cards per row
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                    modifier = Modifier.padding(5.dp),
                    content = {
                        if (vm.villagerList.isEmpty()) {
                            item {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .wrapContentSize(Alignment.Center)
                                )
                            }
                        }

                        items(items = vm.villagerList) { villager ->
                            Box(modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(8.dp)
                                .clickable {
                                    vm.onVillagerSelected(villager)
                                    navigationController.navigate(Screen.DetailedVillagersScreen.route)
                                }
                            ) {
                                CustomImageCard(
                                    imageUrl = villager.imageUrl,
                                    cardTitle = villager.name
                                )
                            }
                        }
                    }
                )
            } else {
                Text(vm.errorMassage)
            }
        }
    )
}


//@Preview
//@Composable
//fun VillagerScreenPreview() {
//    VillagerScreen(navigationController)
//}