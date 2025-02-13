package com.example.taskb.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskb.presentation.contract.Contract
import com.example.taskb.presentation.navigation.event.ScreenAEvent
import com.example.taskb.presentation.navigation.component.ScreenAComponent

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun FirstScreen(component: ScreenAComponent, viewModel: ScreensViewModel = viewModel()) {
    val event = viewModel.event.value
    val state = viewModel.state.value
    val imageUrls = viewModel.imageUrls.value

    LaunchedEffect(event) {
        if (event is Contract.Event.Navigated) {
            component.onEvent(ScreenAEvent.ClickButtonA)
            viewModel.clearEvent()
        }
    }
    
    when (state) {
        is Contract.States.Loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        is Contract.States.Success -> LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(imageUrls) { imageUrl -> ImageItem(imageUrl, component ) }
        }
        is Contract.States.Error -> Text("error: ${(state).message}", modifier = Modifier.padding(16.dp))
        else -> {}
    }
    Box (modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterEnd){
        IconButton(
            onClick = { viewModel.invokeAction(Contract.Action.NavigateToScreenB)

            }
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowRight,
                modifier = Modifier.size(200.dp),
                tint = Color.Blue,
                contentDescription = "Next"
            )
        }

    }
}

@Composable

fun ImageItem(imageUrl: String, component: ScreenAComponent,) {
    Image(
        painter = rememberAsyncImagePainter(imageUrl),
        contentDescription = "Image",
        contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
    )

}
