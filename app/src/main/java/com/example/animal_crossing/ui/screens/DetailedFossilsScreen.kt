package com.example.animal_crossing.ui.screens

import android.content.SharedPreferences
import android.util.Log
import androidx.compose.animation.core.rememberInfiniteTransition
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
import com.example.animal_crossing.data.api.viewModel.FossilViewModel
import com.example.animal_crossing.ui.customComposables.ProfileHeader
import com.example.animal_crossing.ui.customComposables.ProfileProperty
import com.example.animal_crossing.ui.customComposables.PulsatingHeart
import com.example.animal_crossing.ui.customComposables.Title

@Composable
fun DetailedFossilsScreen(vm: FossilViewModel, sharedPreferences: SharedPreferences) {
    val scrollState = rememberScrollState()
    val fossil = vm.selectedFossil
    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    ProfileHeader(image = fossil.imageUrl)
                    ProfileContent(
                        vm = vm,
                        containerHeight = this@BoxWithConstraints.maxHeight,
                        sharedPreferences = sharedPreferences
                    )
                }
            }
        }
    }
}

@Composable
private fun ProfileContent(
    vm: FossilViewModel,
    containerHeight: Dp,
    sharedPreferences: SharedPreferences
) {
    var likedItems by rememberSaveable { mutableStateOf(emptyList<String>()) }
    val fossils = vm.selectedFossil

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

    val infiniteTransition = rememberInfiniteTransition()
    val pulsate = PulsatingHeart(infiniteTransition)


    Column {
        Row {
            Title(fossils.name)
            IconButton(
                onClick = {
                    likedItems = if (likedItems.contains(fossils.name)) {
                        likedItems - fossils.name
                    } else {
                        likedItems + fossils.name
                    }
                    Log.d("ProfileContent", "Liked items: $likedItems")
                },
                modifier = Modifier.size(48.dp)
            ) {
                Icon(
                    imageVector = if (likedItems.contains(fossils.name)) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = if (likedItems.contains(fossils.name)) "Liked" else "Not liked",
                    modifier = Modifier
                        .size(pulsate)
                        .offset(
                            x = 10.dp,
                            y = 10.dp
                        )
                )
            }

        }

        ProfileProperty(label = "Url", value = fossils.url)
        ProfileProperty(label = "Fossil Group", value = fossils.fossilGroup)
        ProfileProperty(label = "Interactable", value = fossils.interactable.toString())
        ProfileProperty(label = "Sell", value = fossils.sell.toString())
        ProfileProperty(label = "HHA Base", value = fossils.hhaBase.toString())
        ProfileProperty(label = "Width", value = fossils.width.toString())
        ProfileProperty(label = "Length", value = fossils.length.toString())

        fossils.colors.forEach { color ->
            ProfileProperty(label = "Color", value = color)
        }

        Spacer(modifier = Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}