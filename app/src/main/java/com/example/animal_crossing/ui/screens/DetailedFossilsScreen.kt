package com.example.animal_crossing.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.animal_crossing.data.api.viewModel.FishViewModel
import com.example.animal_crossing.data.api.viewModel.FossilViewModel

@Composable
fun DetailedFossilsScreen(vm: FossilViewModel) {
    val fossil = vm.selectedFossil
    Column() {
        Text(text = fossil.name)
        Text(text = fossil.url)
    }
}