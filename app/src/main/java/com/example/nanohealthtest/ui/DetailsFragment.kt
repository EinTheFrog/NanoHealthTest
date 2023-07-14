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
import com.example.nanohealthtest.R
import com.example.nanohealthtest.databinding.FragmentDetailsBinding
import com.example.nanohealthtest.model.domain.DomainProduct
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

        setProductData(binding, product)
        setArrowBehavior(binding)
        setBackButtonBehavior(binding)

        return binding.root
    }

    private fun setProductData(binding: FragmentDetailsBinding, product: DomainProduct) {
        binding.productImage.background = BitmapDrawable(resources, product.largeImage)
        binding.productNameText.text = product.name
        val priceInAED: Float = product.price / 100.0f
        binding.priceText.text = resources.getString(R.string.price_in_aed, priceInAED)
        binding.reviewsNumberText.text = resources.getString(R.string.reviews_number, product.reviewsAmount)
        val rangedRating: Float = product.rating / 100.0f
        binding.ratingText.text = "$rangedRating"
        binding.ratingBar.rating = rangedRating
    }

    private fun setArrowBehavior(binding: FragmentDetailsBinding) {
        binding.scrollView.setOnScrollChangeListener { _, _, scrollY, _, _ ->
            binding.arrowImage.rotation = min(scrollY.toFloat(), 180f)
        }
    }

    private fun setBackButtonBehavior(binding: FragmentDetailsBinding) {
        binding.backButton.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}