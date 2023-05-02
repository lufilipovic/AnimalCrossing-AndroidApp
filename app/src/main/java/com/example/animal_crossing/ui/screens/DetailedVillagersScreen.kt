package com.example.animal_crossing.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.animal_crossing.data.api.viewModel.VillagerViewModel

@Composable
fun DetailedVillagersScreen(vm: VillagerViewModel) {
    val villager = vm.selectedVillager
    Column() {
        Text(text = villager.name)
        Text(text = villager.clothing)
    }
}