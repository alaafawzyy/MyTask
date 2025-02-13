package com.example.taskb.presentation.navigation.component

import com.arkivanov.decompose.ComponentContext
import com.example.taskb.presentation.navigation.event.ScreenCEvent

class ScreenCComponent  (
    componentContext: ComponentContext,
    private val onGoBack: () -> Unit
): ComponentContext by componentContext {
    fun onEvent(event: ScreenCEvent) {
        when (event) {
            ScreenCEvent.ClickBack -> onGoBack()
        }
    }
}