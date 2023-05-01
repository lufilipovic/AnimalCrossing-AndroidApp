package com.example.animal_crossing.data.api.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animal_crossing.data.api.APIService
import kotlinx.coroutines.launch

class SeaCreatureViewModel: ViewModel() {
    private val _seaCreature = mutableStateListOf<SeaCreatureItem>()
    var errorMassage: String by mutableStateOf("")

    val seaCreature: List<SeaCreatureItem>
        get() = _seaCreature

    fun getAllSeaCreatures() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                _seaCreature.clear()
                _seaCreature.addAll(apiService.getAllSeaCreatures())
            } catch (e: Exception) {
                errorMassage = e.message.toString()
            }
        }
    }
}