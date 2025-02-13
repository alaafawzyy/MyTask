package com.example.taskb.presentation.navigation.component

import com.arkivanov.decompose.ComponentContext
import com.example.taskb.presentation.navigation.event.ScreenAEvent

class ScreenAComponent (
    componentContext: ComponentContext,
    private val onNavigateToScreenB: () -> Unit
): ComponentContext by componentContext {

    fun onEvent(event: ScreenAEvent) {
        when (event) {
            ScreenAEvent.ClickButtonA -> onNavigateToScreenB()
        }
    }

}