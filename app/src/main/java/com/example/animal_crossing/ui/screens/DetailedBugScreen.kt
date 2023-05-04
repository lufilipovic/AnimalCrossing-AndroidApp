package com.example.animal_crossing.ui.screens

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import com.example.animal_crossing.data.api.viewModel.BugViewModel
import com.example.animal_crossing.ui.customComposables.ProfileHeader
import com.example.animal_crossing.ui.customComposables.ProfileProperty
import com.example.animal_crossing.ui.customComposables.Title
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Composable
fun DetailedBugScreen(vm: BugViewModel, sharedPreferences: SharedPreferences) {

    val scrollState = rememberScrollState()
    val bug = vm.selectedBug
    //val likedItems = sharedPreferences.getStringSet("likedItems", emptySet()) ?: emptySet()

    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    ProfileHeader(image = bug.imageUrl, containerHeight = this@BoxWithConstraints.maxHeight)
                    ProfileContent(vm = vm, containerHeight = this@BoxWithConstraints.maxHeight, sharedPreferences = sharedPreferences)
                }
            }
        }
    }
}


@Composable
private fun ProfileContent(
    vm: BugViewModel,
    containerHeight: Dp,
    sharedPreferences: SharedPreferences
) {
    var likedItems by rememberSaveable { mutableStateOf(emptyList<String>()) }
    val bug = vm.selectedBug

    // Load liked items from SharedPreferences when the composable is recomposed
    LaunchedEffect(sharedPreferences) {
        val likedItemsSet = sharedPreferences.getStringSet("liked_items", emptySet())
        if (likedItemsSet != null) {
            likedItems = likedItemsSet.toList()
        }
    }

    // Save liked items to SharedPreferences whenever the list changes
    LaunchedEffect(likedItems, sharedPreferences) {
        sharedPreferences.edit {
            putStringSet("liked_items", likedItems.toSet())
        }
    }

    Column {
        Row {
            Title(bug.name)
            IconButton(
                onClick = {
                    likedItems = if (likedItems.contains(bug.name)) {
                        likedItems - bug.name
                    } else {
                        likedItems + bug.name
                    }
                    Log.d("ProfileContent", "Liked items: $likedItems")
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = if (likedItems.contains(bug.name)) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (likedItems.contains(bug.name)) "Liked" else "Not liked"
                )
            }

        }

        ProfileProperty(label = "Location", value = bug.location)
        ProfileProperty(label = "Months (Northern Hemisphere)", value = bug.north.months)
        ProfileProperty(label = "Months (Southern Hemisphere)", value = bug.south.months)
        ProfileProperty(label = "Url", value = bug.url)

        bug.catchphrases.forEach { catchphrase ->
            ProfileProperty(label = "Catchphrase", value = catchphrase)
        }

        ProfileProperty(label = "Sell Nook", value = bug.sellNook.toString())
        ProfileProperty(label = "Sell Flick", value = bug.sellFlick.toString())
        ProfileProperty(label = "Tank Width", value = bug.tankWidth.toString())
        ProfileProperty(label = "Tank Length", value = bug.tankLength.toString())

        Spacer(modifier = Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}




