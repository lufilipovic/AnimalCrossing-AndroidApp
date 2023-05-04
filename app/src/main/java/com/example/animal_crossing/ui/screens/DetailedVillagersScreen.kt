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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.content.edit
import com.example.animal_crossing.data.api.viewModel.FossilViewModel
import com.example.animal_crossing.data.api.viewModel.VillagerViewModel
import com.example.animal_crossing.ui.customComposables.ProfileHeader
import com.example.animal_crossing.ui.customComposables.ProfileProperty
import com.example.animal_crossing.ui.customComposables.Title

@Composable
fun DetailedVillagersScreen(vm: VillagerViewModel, sharedPreferences: SharedPreferences) {
    val scrollState = rememberScrollState()
    val villager = vm.selectedVillager
    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    ProfileHeader(image = villager.imageUrl, containerHeight = this@BoxWithConstraints.maxHeight)
                    ProfileContent(vm = vm, containerHeight = this@BoxWithConstraints.maxHeight, sharedPreferences)
                }
            }
        }
    }
}

@Composable
private fun ProfileContent(
    vm: VillagerViewModel,
    containerHeight: Dp,
    sharedPreferences: SharedPreferences
) {
    var likedItems by rememberSaveable { mutableStateOf(emptyList<String>()) }
    val villagers = vm.selectedVillager

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

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
            Title(villagers.name)
            IconButton(
                onClick = {
                    likedItems = if (likedItems.contains(villagers.name)) {
                        likedItems - villagers.name
                    } else {
                        likedItems + villagers.name
                    }
                    Log.d("ProfileContent", "Liked items: $likedItems")
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = if (likedItems.contains(villagers.name)) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (likedItems.contains(villagers.name)) "Liked" else "Not liked"
                )
            }

        }
        ProfileProperty(label = "url", value = villagers.url)
        ProfileProperty(label = "Species", value = villagers.species)
        ProfileProperty(label = "Personality", value = villagers.personality)
        ProfileProperty(label = "Gender", value = villagers.gender)
        ProfileProperty(label = "Birthday Month And Day", value = villagers.birthdayMonth + " "+ villagers.birthdayDay)
        ProfileProperty(label = "Sign", value = villagers.sign)
        ProfileProperty(label = "Quote", value = villagers.quote)
        ProfileProperty(label = "Phrase", value = villagers.phrase)
        ProfileProperty(label = "Islander", value = villagers.islander.toString())
        ProfileProperty(label = "Debut", value = villagers.debut)


        Spacer(modifier = Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}