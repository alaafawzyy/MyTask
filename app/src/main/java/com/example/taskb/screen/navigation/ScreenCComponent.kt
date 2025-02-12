package com.example.taskb.screen.navigation

import com.arkivanov.decompose.ComponentContext

class ScreenCComponent  (
    componentContext: ComponentContext,
    private val onGoBack: () -> Unit
): ComponentContext by componentContext {

}