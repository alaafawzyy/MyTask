package com.example.taskb.screen.event

sealed interface ScreenAEvent {
    data object ClickButtonA: ScreenAEvent
    data object RefreshImages : ScreenAEvent
}