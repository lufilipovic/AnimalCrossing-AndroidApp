package com.example.animal_crossing.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.animal_crossing.data.api.viewModel.BugViewModel
import com.example.animal_crossing.data.api.viewModel.FossilViewModel
import com.example.animal_crossing.data.api.viewModel.SeaCreatureViewModel
import com.example.animal_crossing.ui.customComposables.ProfileHeader
import com.example.animal_crossing.ui.customComposables.ProfileProperty
import com.example.animal_crossing.ui.customComposables.Title


@Composable
fun DetailedSeaCreaturesScreen(vm: SeaCreatureViewModel) {
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
                    ProfileContent(vm = vm, containerHeight = this@BoxWithConstraints.maxHeight)
                }
            }
        }
    }
}

@Composable
private fun ProfileContent(
    vm: SeaCreatureViewModel,
    containerHeight: Dp
) {
    val seaCreature = vm.selectedSeaCreature
    Column {
        Title(seaCreature.name)
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