package com.example.taskb.screen.navigation

import com.arkivanov.decompose.ComponentContext

class ScreenBComponent  (
    componentContext: ComponentContext,
   private val onNavigateToScreenC: (String) -> Unit,
    private val onGoBack: () -> Unit
): ComponentContext by componentContext {

}