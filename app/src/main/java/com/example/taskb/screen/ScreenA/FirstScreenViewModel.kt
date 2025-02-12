package com.example.taskb.screen.ScreenA

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskb.network.RetrofitInstance
import com.example.taskb.contract.Contract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FirstScreenViewModel : ViewModel(), Contract.ViewModel {

    private val _state = mutableStateOf<Contract.States>(Contract.States.Idle)
    override val state: State<Contract.States> get() = _state

    private val _event = mutableStateOf<Contract.Event>(Contract.Event.Idle)
    override val event: State<Contract.Event> get() = _event

    private val _imageUrls = MutableStateFlow<List<String>>(emptyList())
    val imageUrls: StateFlow<List<String>> get() = _imageUrls



    init {
        fetchImages()
    }

    private fun fetchImages() {
        _state.value = Contract.States.Loading
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getImages()
                _imageUrls.value = response.photos.map { it.url }
                _state.value = Contract.States.Success
            } catch (e: Exception) {
                _state.value = Contract.States.Error(e.message ?: "error in fetch image")
            }
        }
    }

    override fun invokeAction(action: Contract.Action) {
        when (action) {
            is Contract.Action.NavigateToScreenB -> {
                _event.value = Contract.Event.Navigated
            }
        }
    }
}
