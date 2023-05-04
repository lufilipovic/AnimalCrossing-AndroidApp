package com.example.animal_crossing.ui.screens

import android.annotation.SuppressLint
import android.content.SharedPreferences
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.animal_crossing.ui.customComposables.BottomBar

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
                title = { Row { Text(text = "Favorites Page") } },
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
                            Text(
                                likedItem,
                                fontSize = 16.sp,
                                modifier = Modifier
                                    .padding(10.dp)
                                    .clip(RoundedCornerShape(8.dp))
                            )
                        }
                    }
                }
            }
        }
    )
}
