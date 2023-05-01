package com.example.animal_crossing.data.api.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animal_crossing.data.api.APIService
import kotlinx.coroutines.launch

class FishViewModel: ViewModel() {
    private val _fishList = mutableStateListOf<FishItem>()
    var errorMassage: String by mutableStateOf("")

    val fishList: List<FishItem>
        get() = _fishList

    fun getAllFish() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                _fishList.clear()
                _fishList.addAll(apiService.getAllFish())
            } catch (e: Exception) {
                errorMassage = e.message.toString()
            }
        }
    }
}