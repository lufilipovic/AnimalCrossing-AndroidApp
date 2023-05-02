package com.example.animal_crossing.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.example.animal_crossing.data.api.viewModel.BugViewModel

@Composable
fun DetailedBugScreen(vm: BugViewModel) {
    val bug = vm.selectedBug
    Column() {
        Text(text = bug.name)
        Text(text = bug.location)
    }
}