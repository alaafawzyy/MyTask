package com.example.taskb.screen.navigation

import com.arkivanov.decompose.ComponentContext
import com.example.taskb.screen.event.ScreenAEvent

class ScreenAComponent (
    componentContext: ComponentContext,
    private val onNavigateToScreenB: () -> Unit
): ComponentContext by componentContext {

    fun onEvent(event: ScreenAEvent) {
        when (event) {
            ScreenAEvent.ClickButtonA -> onNavigateToScreenB()
            ScreenAEvent.RefreshImages -> TODO()
        }
    }

}