package com.example.animal_crossing.ui.screens

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.animal_crossing.data.api.viewModel.BugViewModel
import com.example.animal_crossing.ui.customComposables.ProfileHeader
import com.example.animal_crossing.ui.customComposables.ProfileProperty
import com.example.animal_crossing.ui.customComposables.Title

@Composable
fun DetailedBugScreen(vm: BugViewModel) {

    val scrollState = rememberScrollState()
    val bug = vm.selectedBug

    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    ProfileHeader(image = bug.imageUrl, containerHeight = this@BoxWithConstraints.maxHeight)
                    ProfileContent(vm = vm, containerHeight = this@BoxWithConstraints.maxHeight)
                }
            }
        }
    }
}

@Composable
private fun ProfileContent(
    vm: BugViewModel,
    containerHeight: Dp
) {
    var likedItems by rememberSaveable { mutableStateOf(emptyList<String>()) }
    val bug = vm.selectedBug
    Column{
        Row {
            Title(bug.name)
            IconButton(
                onClick = {
                    if (!likedItems.contains(bug.name)) {
                        likedItems = likedItems + bug.name
                        Log.d("ProfileContent", "Liked items: $likedItems")
                    }
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



