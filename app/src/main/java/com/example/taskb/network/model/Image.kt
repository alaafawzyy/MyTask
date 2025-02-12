package com.example.taskb.network.model


    data class ImageResponse(
        val photos: List<ImageItem>
    )

    data class ImageItem(
        val id: Int,
        val url: String
    )
