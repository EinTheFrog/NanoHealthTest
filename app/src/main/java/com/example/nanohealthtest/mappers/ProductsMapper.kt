package com.example.nanohealthtest.mappers

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import com.example.nanohealthtest.model.data.DataProduct
import com.example.nanohealthtest.model.domain.DomainProduct
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withContext
import java.lang.Exception
import kotlin.coroutines.resumeWithException

object ProductsMapper {
    suspend fun dataToDomain(dataProduct: DataProduct): DomainProduct = withContext(Dispatchers.Main) {
        return@withContext DomainProduct(
            id = dataProduct.id,
            name = dataProduct.name,
            description = dataProduct.description,
            price = dataProduct.price,
            reviewsAmount = dataProduct.rating.count,
            rating = dataProduct.rating.rate,
            imageUrl = dataProduct.imageUrl
        )
    }
}