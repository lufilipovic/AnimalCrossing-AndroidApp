package com.example.animal_crossing.data.api.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animal_crossing.data.api.APIService
import com.example.animal_crossing.data.api.model.FossilItem
import kotlinx.coroutines.launch

class FossilViewModel : ViewModel() {
    lateinit var selectedFossil: FossilItem
    val onFossilSelected: (fossil: FossilItem) -> Unit = {
        selectedFossil = it
    }

    private val _fossilList = mutableStateListOf<FossilItem>()
    var errorMassage: String by mutableStateOf("")

    val fossilList: List<FossilItem>
        get() = _fossilList

    fun getAllFossils() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                _fossilList.clear()
                _fossilList.addAll(apiService.getAllFossils())
            } catch (e: Exception) {
                errorMassage = e.message.toString()
            }
        }
    }
}