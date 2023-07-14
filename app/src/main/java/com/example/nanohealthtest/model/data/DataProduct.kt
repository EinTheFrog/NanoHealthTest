package com.example.nanohealthtest.model.data

data class DataProduct(
    val id: Int,
    val name: String,
    val description: String,
    val price: Float,
    val reviewsAmount: Int,
    val rating: Float,
    val imageUrl: String
)