package com.example.taskb.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.taskb.presentation.contract.Contract
import com.example.taskb.presentation.navigation.component.ScreenCComponent
import com.example.taskb.presentation.navigation.event.ScreenCEvent

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun ThirdScreen(component: ScreenCComponent, viewModel: ScreensViewModel = viewModel()) {
    val state = viewModel.state.value
    val resourceImages by viewModel.imageresource.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.invokeAction(Contract.Action.LoadImagesFromResources)
    }

    when (state) {
        is Contract.States.Loading -> Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
        is Contract.States.Success -> LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(resourceImages) { imageresource ->
                ImageResourceItem(imageresource)
            }
        }
        is Contract.States.Error -> Text("error: ${state.message}", modifier = Modifier.padding(16.dp))
        else -> {}
    }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.CenterStart) {
        IconButton(
            onClick = { component.onEvent(ScreenCEvent.ClickBack) }
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                modifier = Modifier.size(50.dp),
                tint = Color.Blue,
                contentDescription = "Back"
            )
        }
    }
}

@Composable
fun ImageResourceItem(imageres: Int) {
    Image(
        painter = painterResource(id = imageres),
        contentDescription = "image",
       // contentScale = ContentScale.Crop,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
    )
}

