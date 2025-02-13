package com.example.taskb.presentation.navigation.event

sealed interface ScreenAEvent {
    data object ClickButtonA: ScreenAEvent
}