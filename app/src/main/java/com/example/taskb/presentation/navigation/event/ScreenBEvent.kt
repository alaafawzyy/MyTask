package com.example.taskb.presentation.navigation.event

sealed interface ScreenBEvent {
    data object ClickButtonB: ScreenBEvent
    data object ClickBack : ScreenBEvent
}