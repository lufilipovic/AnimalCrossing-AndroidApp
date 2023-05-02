package com.example.animal_crossing.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.animal_crossing.data.api.viewModel.FishViewModel


@Composable
fun DetailedFishScreen(vm: FishViewModel) {
    val fish = vm.selectedFish
    Column() {
        Text(text = fish.name)
        Text(text = fish.location)
    }
}