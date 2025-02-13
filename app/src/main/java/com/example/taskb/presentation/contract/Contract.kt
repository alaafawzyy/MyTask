package com.example.taskb.presentation.contract

import android.content.Context


object Contract {
    interface ViewModel {
        fun invokeAction(action: Action)
        val state: androidx.compose.runtime.State<States>
        val event: androidx.compose.runtime.State<Event>
    }

    sealed interface Action {
        data object NavigateToScreenB : Action
        data object NavigateToScreenC : Action
        data object LoadImagesFromInternet : Action
        data class LoadImagesFromDisk(val context: Context) : Action
        data object LoadImagesFromResources : Action
    }



    sealed interface Event {
        data object Idle : Event
        data object Navigated : Event
        data class ShowError(val message: String) : Event
    }


    sealed interface States {
        data object Idle : States
        data object Loading : States
        data class Error(val message: String) : States
        data class Success(val images: List<String>) : States
    }
}
