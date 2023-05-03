package com.example.animal_crossing.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
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
    val bug = vm.selectedBug
    Column {
        Title(bug.name)
        ProfileProperty(label = "Location", value = bug.location)
        ProfileProperty(label = "Months (Northern Hemisphere)", value = bug.north.months)
        ProfileProperty(label = "Months (South Hemisphere)", value = bug.south.months)
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



