package com.example.animal_crossing.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.animal_crossing.data.api.viewModel.BugViewModel
import com.example.animal_crossing.ui.customComposables.BottomBar
import com.example.animal_crossing.ui.customComposables.CustomImageCard
import com.example.animal_crossing.ui.navigation.NavDrawerItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter", "StateFlowValueCalledInComposition")
@Composable
fun BugsScreen(navigationController: NavHostController, vm: BugViewModel) {
    var searchQuery by remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit, block = {
        vm.getAllBugs()
    })

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Row { Text(text = "All ACNH Bugs") } },
            )
        },
        bottomBar = { BottomBar(navController = navigationController) },


        content = {
            Column {
                Spacer(modifier = Modifier.height(8.dp))
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    value = searchQuery,
                    onValueChange = { newSearchQuery ->
                        searchQuery = newSearchQuery
                    },
                    placeholder = { Text("Search") },
                    leadingIcon = {
                        Icon(Icons.Filled.Search, contentDescription = "Search")
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = MaterialTheme.colors.background,
                        cursorColor = MaterialTheme.colors.primary,
                        focusedIndicatorColor = MaterialTheme.colors.primary,
                        unfocusedIndicatorColor = Color.Gray,
                        textColor = MaterialTheme.colors.onSurface
                    )
                )

                if (vm.errorMassage.isEmpty()) {

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2), // Display 2 cards per row
                        contentPadding = PaddingValues(horizontal = 8.dp, vertical = 8.dp),
                        modifier = Modifier.padding(5.dp),
                        content = {
                            if (vm.bugList.isEmpty()) {
                                item {
                                    CircularProgressIndicator(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .wrapContentSize(Alignment.Center)
                                    )
                                }
                            }

                            items(items = vm.bugList.filter { bug ->
                                bug.name.contains(searchQuery, ignoreCase = true)
                            }) { bug ->
                                Box(modifier = Modifier
                                    .fillMaxWidth(0.5f)
                                    .padding(8.dp)
                                    .clickable {
                                        vm.onBugSelected(bug)
                                        navigationController.navigate(NavDrawerItem.DetailedBugScreen.route)
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
        }
    )
}
