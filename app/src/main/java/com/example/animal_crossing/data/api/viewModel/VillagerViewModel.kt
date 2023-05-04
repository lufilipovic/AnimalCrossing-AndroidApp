package com.example.animal_crossing.data.api.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animal_crossing.data.api.APIService
import com.example.animal_crossing.data.api.model.VillagerItem
import kotlinx.coroutines.launch

class VillagerViewModel : ViewModel() {
    lateinit var selectedVillager: VillagerItem
    val onVillagerSelected: (villager: VillagerItem) -> Unit = {
        selectedVillager = it
    }

    private val _villagerList = mutableStateListOf<VillagerItem>()
    var errorMassage: String by mutableStateOf("")

    val villagerList: List<VillagerItem>
        get() = _villagerList

    fun getAllVillagers() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                _villagerList.clear()
                _villagerList.addAll(apiService.getAllVillagers())
            } catch (e: Exception) {
                errorMassage = e.message.toString()
            }
        }
    }
}