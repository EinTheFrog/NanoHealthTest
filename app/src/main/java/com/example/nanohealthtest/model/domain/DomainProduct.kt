package com.example.nanohealthtest.model.domain

import android.graphics.Bitmap

data class DomainProduct(
    val id: Long,
    val name: String,
    val description: String,
    val price: Int,
    val reviewsAmount: Int,
    val rating: Int,
    val image: Bitmap?,
    val largeImage: Bitmap?
)