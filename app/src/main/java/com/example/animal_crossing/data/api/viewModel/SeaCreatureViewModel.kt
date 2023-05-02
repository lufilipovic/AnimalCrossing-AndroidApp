package com.example.animal_crossing.data.api.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animal_crossing.data.api.APIService
import com.example.animal_crossing.data.api.model.SeaCreatureItem
import kotlinx.coroutines.launch

class SeaCreatureViewModel: ViewModel() {
    private val _seaCreatureList = mutableStateListOf<SeaCreatureItem>()
    var errorMassage: String by mutableStateOf("")

    val seaCreatureList: List<SeaCreatureItem>
        get() = _seaCreatureList

    fun getAllSeaCreatures() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                _seaCreatureList.clear()
                _seaCreatureList.addAll(apiService.getAllSeaCreatures())
            } catch (e: Exception) {
                errorMassage = e.message.toString()
            }
        }
    }
}