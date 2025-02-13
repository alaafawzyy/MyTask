package com.example.taskb.presentation.navigation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.ExperimentalDecomposeApi
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.push
import android.os.Parcelable
import com.example.taskb.presentation.navigation.component.ScreenAComponent
import com.example.taskb.presentation.navigation.component.ScreenBComponent
import com.example.taskb.presentation.navigation.component.ScreenCComponent
import kotlinx.parcelize.Parcelize


class RootComponent(
    componentContext: ComponentContext
) : ComponentContext by componentContext {

    private val navigation = StackNavigation<Configuration>()
    val childStack = childStack(
        source = navigation,
        initialConfiguration = Configuration.ScreenA,
        handleBackButton = true,
        childFactory = ::createChild
    )

    @OptIn(ExperimentalDecomposeApi::class)
    private fun createChild(
        config: Configuration,
        context: ComponentContext
    ): Child {
        return when (config) {
            Configuration.ScreenA -> Child.ScreenA(
                ScreenAComponent(
                    componentContext = context,
                    onNavigateToScreenB = {
                        navigation.push(Configuration.ScreenB)
                    }
                )
            )
            Configuration.ScreenB -> Child.ScreenB(
                ScreenBComponent(
                    componentContext = context,
                    onNavigateToScreenC = { navigation.push(Configuration.ScreenC) },
                    onGoBack = {
                        navigation.pop()
                    }
                )
            )
            Configuration.ScreenC -> Child.ScreenC(
                ScreenCComponent(
                    componentContext = context,
                    onGoBack = {
                        navigation.pop()
                    }
                )
            )
        }
    }

    sealed class Child {
        data class ScreenA(val component: ScreenAComponent) : Child()
        data class ScreenB(val component: ScreenBComponent) : Child()
        data class ScreenC(val component: ScreenCComponent) : Child()
    }


    @Parcelize
    sealed class Configuration : Parcelable {
        @Parcelize
        data object ScreenA : Configuration()

        @Parcelize
        data object ScreenB : Configuration()

        @Parcelize
        data object ScreenC : Configuration()
    }

}
