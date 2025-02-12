package com.example.taskb.contract


class Contract {
    interface ViewModel {
        fun invokeAction(action: Action)
        val state: androidx.compose.runtime.State<States>
        val event: androidx.compose.runtime.State<Event>
    }

    sealed interface Action {
        data object NavigateToScreenB : Action
        data object NavigateToScreenC : Action

    }



    sealed interface Event {
        data object Idle : Event
        data object Navigated : Event
    }


    sealed interface States {
        data object Idle : States
        data object Loading : States
        data class Error(val message: String) : States
        data object Success: States
    }
}
