package com.example.animal_crossing.ui.screens

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.animal_crossing.ui.customComposables.BottomBar
import com.example.animal_crossing.ui.customComposables.PulsatingHeart


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun Favorites(navigationController: NavHostController, sharedPreferences: SharedPreferences) {
    var likedItems by rememberSaveable { mutableStateOf(emptyList<String>()) }

    LaunchedEffect(sharedPreferences) {
        val likedItemsSet = sharedPreferences.getStringSet("liked_items", emptySet())
        if (likedItemsSet != null) {
            likedItems = likedItemsSet.toList()
        }
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row {
                        Text(text = "Favorites Page")
                    }
                },
            )

        },
        bottomBar = { BottomBar(navController = navigationController) },


        content = {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                likedItems.forEach { likedItem ->
                    Card(
                        modifier = Modifier
                            .padding(all = 10.dp)
                            .fillMaxWidth()
                            .shadow(
                                elevation = 8.dp,
                                shape = RoundedCornerShape(16.dp),
                                spotColor = MaterialTheme.colors.primary // Change the shadow color to red
                            )

                    ) {
                        Column(
                            modifier = Modifier
                                .padding(all = 10.dp)
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.SpaceBetween
                        ) {

                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    likedItem,
                                    fontSize = 16.sp,
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                )
                                IconButton(onClick = {
                                    // Remove the item from the list
                                    likedItems = likedItems.filter { it != likedItem }

                                    // Update the shared preferences
                                    sharedPreferences.edit()
                                        .putStringSet("liked_items", likedItems.toSet())
                                        .apply()
                                }) {
                                    Icon(Icons.Default.Delete, contentDescription = "Delete")
                                }
                            }
                        }
                    }
                }
            }
        }
    )
}

