package com.example.animal_crossing.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.animal_crossing.data.api.viewModel.FossilViewModel
import com.example.animal_crossing.data.api.viewModel.VillagerViewModel
import com.example.animal_crossing.ui.customComposables.ProfileHeader
import com.example.animal_crossing.ui.customComposables.ProfileProperty
import com.example.animal_crossing.ui.customComposables.Title

@Composable
fun DetailedVillagersScreen(vm: VillagerViewModel) {
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
                    ProfileContent(vm = vm, containerHeight = this@BoxWithConstraints.maxHeight)
                }
            }
        }
    }
}

@Composable
private fun ProfileContent(
    vm: VillagerViewModel,
    containerHeight: Dp
) {
    val villagers = vm.selectedVillager
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(villagers.name)
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