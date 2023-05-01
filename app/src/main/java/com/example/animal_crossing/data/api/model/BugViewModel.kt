package com.example.animal_crossing.data.api.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.animal_crossing.data.api.APIService
import kotlinx.coroutines.launch

class BugViewModel: ViewModel() {
    private val _bugList = mutableStateListOf<BugItem>()
    var errorMassage: String by mutableStateOf("")

    val bugList: List<BugItem>
        get() = _bugList

    fun getAllBugs() {
        viewModelScope.launch {
            val apiService = APIService.getInstance()
            try {
                _bugList.clear()
                _bugList.addAll(apiService.getAllBugs())
            } catch (e: Exception) {
                errorMassage = e.message.toString()
            }
        }
    }
}