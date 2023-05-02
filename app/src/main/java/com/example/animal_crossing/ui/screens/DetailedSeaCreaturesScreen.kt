package com.example.animal_crossing.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.animal_crossing.data.api.viewModel.FossilViewModel
import com.example.animal_crossing.data.api.viewModel.SeaCreatureViewModel


@Composable
fun DetailedSeaCreaturesScreen(vm: SeaCreatureViewModel) {
    val seaCreature = vm.selectedSeaCreature
    Column() {
        Text(text = seaCreature.name)
        Text(text = seaCreature.url)
    }
}