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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.animal_crossing.data.api.viewModel.BugViewModel
import com.example.animal_crossing.ui.customComposables.CustomImageCard
import com.example.animal_crossing.ui.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BugsScreen(navigationController: NavHostController, vm: BugViewModel) {


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
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2), // Display 2 cards per row
                    contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                    modifier = Modifier.padding(5.dp),
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
                            Box(modifier = Modifier
                                .fillMaxWidth(0.5f)
                                .padding(8.dp)
                                .clickable {
                                    vm.onBugSelected(bug)
                                    navigationController.navigate(Screen.DetailedBugScreen.route)
                                }
                            ) {
                                CustomImageCard(
                                    imageUrl = bug.imageUrl,
                                    cardTitle = bug.name
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
//fun BugsScreenPreview() {
//    BugsScreen(navigationController)
//}