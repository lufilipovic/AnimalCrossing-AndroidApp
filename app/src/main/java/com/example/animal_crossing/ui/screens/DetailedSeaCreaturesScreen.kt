package com.example.animal_crossing.ui.screens

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
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
import com.example.animal_crossing.data.api.viewModel.FossilViewModel
import com.example.animal_crossing.data.api.viewModel.SeaCreatureViewModel
import com.example.animal_crossing.ui.customComposables.ProfileHeader
import com.example.animal_crossing.ui.customComposables.ProfileProperty
import com.example.animal_crossing.ui.customComposables.Title


@Composable
fun DetailedSeaCreaturesScreen(vm: SeaCreatureViewModel, sharedPreferences: SharedPreferences) {
    val scrollState = rememberScrollState()
    val seaCreature = vm.selectedSeaCreature
    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    ProfileHeader(image = seaCreature.imageUrl, containerHeight = this@BoxWithConstraints.maxHeight)
                    ProfileContent(vm = vm, containerHeight = this@BoxWithConstraints.maxHeight, sharedPreferences = sharedPreferences)
                }
            }
        }
    }
}

@Composable
private fun ProfileContent(
    vm: SeaCreatureViewModel,
    containerHeight: Dp,
    sharedPreferences: SharedPreferences
) {
    var likedItems by rememberSaveable { mutableStateOf(emptyList<String>()) }
    val seaCreature = vm.selectedSeaCreature

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
            Title(seaCreature.name)
            IconButton(
                onClick = {
                    likedItems = if (likedItems.contains(seaCreature.name)) {
                        likedItems - seaCreature.name
                    } else {
                        likedItems + seaCreature.name
                    }
                    Log.d("ProfileContent", "Liked items: $likedItems")
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = if (likedItems.contains(seaCreature.name)) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (likedItems.contains(seaCreature.name)) "Liked" else "Not liked"
                )
            }

        }
        ProfileProperty(label = "Shadow Movement", value = seaCreature.shadowMovement)
        ProfileProperty(label = "Shadow Size", value = seaCreature.shadowSize)
        ProfileProperty(label = "Months (Northern Hemisphere)", value = seaCreature.north.months)
        ProfileProperty(label = "Months (Southern Hemisphere)", value = seaCreature.south.months)
        ProfileProperty(label = "Url", value = seaCreature.url)

        seaCreature.catchphrases.forEach { catchphrase ->
            ProfileProperty(label = "Catchphrase", value = catchphrase)
        }

        ProfileProperty(label = "Sell Nook", value = seaCreature.sellNook.toString())
        ProfileProperty(label = "Tank Width", value = seaCreature.tankWidth.toString())
        ProfileProperty(label = "Tank Length", value = seaCreature.tankLength.toString())

        Spacer(modifier = Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}