package com.example.taskb.presentation.screen

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.taskb.R
import com.example.taskb.network.RetrofitInstance
import com.example.taskb.presentation.contract.Contract
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.File

class ScreensViewModel : ViewModel(), Contract.ViewModel {

    private val _state = mutableStateOf<Contract.States>(Contract.States.Idle)
    override val state: State<Contract.States> get() = _state

    private val _event = mutableStateOf<Contract.Event>(Contract.Event.Idle)
    override val event: State<Contract.Event> get() = _event

    override fun invokeAction(action: Contract.Action) {
        when (action) {
            is Contract.Action.NavigateToScreenB -> {
                _event.value = Contract.Event.Navigated
            }
            is Contract.Action.NavigateToScreenC -> {
                _event.value = Contract.Event.Navigated
            }
            is Contract.Action.LoadImagesFromInternet -> {
                fetchImagesFromUrl()
            }
            is Contract.Action.LoadImagesFromDisk -> {
                loadImagesFromDisk(action.context)
            }
            is Contract.Action.LoadImagesFromResources -> {
                fetchImagesFromResources()
            }
        }
    }

    init {
        fetchImagesFromUrl()
    }

    fun clearEvent() {
        _event.value = Contract.Event.Idle
    }

    private val _imageUrls = MutableStateFlow<List<String>>(emptyList())
    val imageUrls: StateFlow<List<String>> get() = _imageUrls

    private val _imageresource = MutableStateFlow<List<Int>>(emptyList())
    val imageresource: StateFlow<List<Int>> get() = _imageresource

    private val _imageDisk = MutableStateFlow<List<String>>(emptyList())
    val imageDisk: StateFlow<List<String>> get() = _imageDisk


    private fun fetchImagesFromUrl() {
        _state.value = Contract.States.Loading
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getImages()
                val images = response.photos.map { it.url }
                _imageUrls.value = images
                _state.value = Contract.States.Success(images)
            } catch (e: Exception) {
                _state.value = Contract.States.Error(e.message ?: "error in fetching images")
            }
        }
    }

    private fun fetchImagesFromResources() {
        _state.value = Contract.States.Loading
        viewModelScope.launch {
            try {
                val images = listOf(R.drawable.image1, R.drawable.image2, R.drawable.image3,
                    R.drawable.image4, R.drawable.image5)
                _imageresource.value = images

                val imageStrings = images.map { it.toString() }
                _state.value = Contract.States.Success(imageStrings)
            } catch (e: Exception) {
                _state.value = Contract.States.Error(e.message ?: "error in fetching images")
            }
        }
    }



    fun loadImagesFromDisk(context: Context) {
        _state.value = Contract.States.Loading
        viewModelScope.launch {
            try {
                val imagesDir = File(context.filesDir, "images")
                val images = imagesDir.listFiles()?.map { it.absolutePath } ?: emptyList()
                _imageDisk.value = images
                _state.value = Contract.States.Success(images )
            } catch (e: Exception) {
                _state.value = Contract.States.Error(e.message ?: "Error in fetching images")
            }
        }
    }
}
