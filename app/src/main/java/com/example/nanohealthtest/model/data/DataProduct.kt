package com.example.nanohealthtest.model.data

import com.google.gson.annotations.SerializedName

data class DataProduct(
    val id: Int,
    @SerializedName("title")
    val name: String,
    val description: String,
    val price: Float,
    val rating: DataRating,
    @SerializedName("image")
    val imageUrl: String
)