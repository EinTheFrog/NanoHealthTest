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
        val imageBitmap = loadImage(dataProduct.imageUrl, 360, 240)
        val largeImageBitmap = loadImage(dataProduct.imageUrl, 440, 800)
        return@withContext DomainProduct(
            id = dataProduct.id,
            name = dataProduct.name,
            description = dataProduct.description,
            price = dataProduct.price,
            reviewsAmount = dataProduct.reviewsAmount,
            rating = dataProduct.rating,
            image = imageBitmap,
            largeImage = largeImageBitmap
        )
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    suspend fun loadImage(imageUrl: String, targetWidth: Int, targetHeight: Int): Bitmap? = suspendCancellableCoroutine { continuation ->
        val target = object: Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                continuation.resume(bitmap) {}
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                continuation.resumeWithException(Exception(e))
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {}
        }

        Picasso.get().load(imageUrl).resize(targetWidth, targetHeight).centerCrop().into(target)
    }
}