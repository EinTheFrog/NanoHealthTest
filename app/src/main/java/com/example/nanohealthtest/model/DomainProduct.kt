package com.example.nanohealthtest.model

import android.graphics.Bitmap

data class DomainProduct(
    val id: Long,
    val name: String,
    val description: String,
    val price: Int,
    val rating: Int,
    val image: Bitmap?
)