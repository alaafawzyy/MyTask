package com.example.taskb.presentation.navigation.event

sealed interface ScreenCEvent {
    data object ClickBack : ScreenCEvent
}