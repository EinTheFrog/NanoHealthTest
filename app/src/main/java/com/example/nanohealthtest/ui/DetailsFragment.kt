package com.example.nanohealthtest.ui

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.example.nanohealthtest.databinding.FragmentDetailsBinding
import com.example.nanohealthtest.ui.viewmodel.ProductsViewModel

class DetailsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FragmentDetailsBinding.inflate(inflater)

        val detailsScreenArgs by navArgs<DetailsFragmentArgs>()
        val productId = detailsScreenArgs.productId

        val viewModel by activityViewModels<ProductsViewModel>()
        val product = viewModel.getProduct(productId)

        binding.productImage.background = BitmapDrawable(resources, product.largeImage)
        binding.productNameText.text = product.name
        val priceInAED = product.price / 100
        binding.priceText.text = "$priceInAED AED"
        binding.reviewsNumberText.text = "Reviews (${product.reviewsAmount})"
        val rangedRating = product.rating / 100
        binding.ratingText.text = "$rangedRating/5.0"

        return binding.root
    }
}