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
import com.example.animal_crossing.data.api.viewModel.FishViewModel
import com.example.animal_crossing.data.api.viewModel.FossilViewModel
import com.example.animal_crossing.ui.customComposables.ProfileHeader
import com.example.animal_crossing.ui.customComposables.ProfileProperty
import com.example.animal_crossing.ui.customComposables.Title

@Composable
fun DetailedFossilsScreen(vm: FossilViewModel) {
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
                    ProfileHeader(image = fossil.imageUrl, containerHeight = this@BoxWithConstraints.maxHeight)
                    ProfileContent(vm = vm, containerHeight = this@BoxWithConstraints.maxHeight)
                }
            }
        }
    }
}

@Composable
private fun ProfileContent(
    vm: FossilViewModel,
    containerHeight: Dp
) {
    val fossils = vm.selectedFossil
    Column {
        Title(fossils.name)
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