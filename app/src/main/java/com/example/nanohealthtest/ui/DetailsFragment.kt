package com.example.nanohealthtest.ui

import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.nanohealthtest.databinding.FragmentDetailsBinding
import com.example.nanohealthtest.ui.viewmodel.ProductsViewModel
import java.lang.Float.min

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
        val priceInAED: Float = product.price / 100.0f
        binding.priceText.text = "$priceInAED AED"
        binding.reviewsNumberText.text = "Reviews (${product.reviewsAmount})"
        val rangedRating: Float = product.rating / 100.0f
        binding.ratingText.text = "$rangedRating"
        binding.ratingBar.rating = rangedRating

        binding.scrollView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            binding.arrowImage.rotation = min(scrollY.toFloat(), 180f)
        }

        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }

        return binding.root
    }
}