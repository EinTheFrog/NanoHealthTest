package com.example.nanohealthtest.model.domain

import android.graphics.Bitmap

data class DomainProduct(
    val id: Int,
    val name: String,
    val description: String,
    val price: Float,
    val reviewsAmount: Int,
    val rating: Float,
    val imageUrl: String
)