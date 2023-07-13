package com.example.nanohealthtest.model

data class DataProduct(
    val id: Long,
    val name: String,
    val description: String,
    val price: Int,
    val rating: Int,
    val imageUrl: String
)