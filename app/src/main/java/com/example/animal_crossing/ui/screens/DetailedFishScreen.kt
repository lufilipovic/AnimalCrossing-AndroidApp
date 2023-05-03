package com.example.animal_crossing.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.animal_crossing.data.api.viewModel.FishViewModel
import com.example.animal_crossing.ui.customComposables.ProfileHeader
import com.example.animal_crossing.ui.customComposables.ProfileProperty
import com.example.animal_crossing.ui.customComposables.Title


@Composable
fun DetailedFishScreen(vm: FishViewModel) {
    val scrollState = rememberScrollState()
    val fish = vm.selectedFish
    Column(modifier = Modifier.fillMaxSize()) {
        BoxWithConstraints {
            Surface {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(scrollState)
                ) {
                    ProfileHeader(image = fish.imageUrl, containerHeight = this@BoxWithConstraints.maxHeight)
                    ProfileContent(vm = vm, containerHeight = this@BoxWithConstraints.maxHeight)
                }
            }
        }
    }
}

@Composable
private fun ProfileContent(
    vm: FishViewModel,
    containerHeight: Dp
) {
    val fish = vm.selectedFish
    Column {
        Title(fish.name)
        ProfileProperty(label = "Location", value = fish.location)
        ProfileProperty(label = "Month (Northern Hemisphere)", value = fish.north.months)
        ProfileProperty(label = "Months (Southern Hemisphere)", value = fish.south.months)
        ProfileProperty(label = "Url", value = fish.url)

        fish.catchphrases.forEach { catchphrase ->
            ProfileProperty(label = "Catchphrase", value = catchphrase)
        }

        ProfileProperty(label = "Sell Nook", value = fish.sellNook.toString())
        ProfileProperty(label = "Sell Cj", value = fish.sellCj.toString())
        ProfileProperty(label = "Tank Width", value = fish.tankWidth.toString())
        ProfileProperty(label = "Tank Length", value = fish.tankLength.toString())

        Spacer(modifier = Modifier.height((containerHeight - 320.dp).coerceAtLeast(0.dp)))
    }
}