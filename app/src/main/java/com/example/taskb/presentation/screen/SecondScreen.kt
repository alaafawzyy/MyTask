package com.example.taskb.presentation.screen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.example.taskb.presentation.contract.Contract
import com.example.taskb.presentation.imageloaderdisk.ImageDownloader
import com.example.taskb.presentation.navigation.component.ScreenBComponent
import com.example.taskb.presentation.navigation.event.ScreenBEvent
import kotlinx.coroutines.delay
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import java.io.File

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SecondScreen(
    component: ScreenBComponent,
    viewModel: ScreensViewModel = viewModel()
) {
    val context = LocalContext.current
    val state = viewModel.state.value
    val event = viewModel.event.value
    val imageDisk by viewModel.imageDisk.collectAsStateWithLifecycle()

    LaunchedEffect(true) {
        if (!ImageDownloader.isImagesSaved(context)) {
            ImageDownloader.saveImagesToInternal(context)
        }

        viewModel.loadImagesFromDisk(context)

        if (event is Contract.Event.Navigated) {
            component.onEvent(ScreenBEvent.ClickButtonB)
            viewModel.clearEvent()
        }
    }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when (state) {
            is Contract.States.Loading -> Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
            is Contract.States.Success -> {
                val imageList = remember(imageDisk) { imageDisk}
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(imageList) { filePath ->
                        ImageItem(filePath)
                    }
                }
            }
            is Contract.States.Error -> Text("Error: ${state.message}")
            else -> {
                Text("No Image in Disk")
            }
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            IconButton(
                onClick = { component.onEvent(ScreenBEvent.ClickBack) }
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowLeft,
                    modifier = Modifier.size(48.dp),
                    tint = Color.Blue,
                    contentDescription = "Previous"
                )
            }

            IconButton(
                onClick = { component.onEvent(ScreenBEvent.ClickButtonB)  }
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowRight,
                    modifier = Modifier.size(48.dp),
                    tint = Color.Blue,
                    contentDescription = "Next"
                )
            }
        }
    }
}

@Composable
fun ImageItem(filePath: String) {
    val context = LocalContext.current
    val file = File(filePath)
    val imageLoader = remember {
        ImageLoader.Builder(context)
            .components {
                add(SvgDecoder.Factory())
            }
            .build()
    }

    AsyncImage(
        model = ImageRequest.Builder(context)
            .data(file)
            .build(),
        contentDescription = "image",
        imageLoader = imageLoader,
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .padding(8.dp)
    )
}