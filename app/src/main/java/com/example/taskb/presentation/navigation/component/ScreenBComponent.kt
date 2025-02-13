package com.example.taskb.presentation.navigation.component

import com.arkivanov.decompose.ComponentContext
import com.example.taskb.presentation.navigation.event.ScreenBEvent

class ScreenBComponent  (
    componentContext: ComponentContext,
   private val onNavigateToScreenC: () -> Unit,
    private val onGoBack: () -> Unit
): ComponentContext by componentContext {
    fun onEvent(event: ScreenBEvent) {
        when (event) {
            ScreenBEvent.ClickButtonB -> onNavigateToScreenC()
            ScreenBEvent.ClickBack -> onGoBack()
        }
    }

}




