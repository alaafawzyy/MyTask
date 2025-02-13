package com.example.taskb.main


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import com.arkivanov.decompose.defaultComponentContext
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.Children
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.slide
import com.arkivanov.decompose.extensions.compose.jetbrains.stack.animation.stackAnimation
import com.arkivanov.decompose.extensions.compose.jetbrains.subscribeAsState

import com.example.taskb.presentation.screen.FirstScreen
import com.example.taskb.presentation.screen.SecondScreen
import com.example.taskb.presentation.screen.ThirdScreen
import com.example.taskb.presentation.navigation.root.RootComponent
import com.example.taskb.ui.theme.TaskBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskBTheme {
                App(root = RootComponent(componentContext = defaultComponentContext()))
            }
        }
    }
    @Composable
    fun App(root: RootComponent) {
        MaterialTheme {
            val childStack by root.childStack.subscribeAsState()
            Children(
                stack = childStack,
                animation = stackAnimation(slide())
            ) { child ->
                when(val instance = child.instance) {
                    is RootComponent.Child.ScreenA -> FirstScreen(instance.component)
                    is RootComponent.Child.ScreenB -> SecondScreen(instance.component)
                    is RootComponent.Child.ScreenC -> ThirdScreen(instance.component)
                }
            }
        }
    }

}

