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
import com.squareup.picasso.Picasso
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
        Picasso.get().load(product.imageUrl).fit().centerCrop().into(binding.productImage)
        binding.productNameText.text = product.name
        binding.priceText.text = resources.getString(R.string.price_in_aed, product.price)
        binding.reviewsNumberText.text = resources.getString(R.string.reviews_number, product.reviewsAmount)
        binding.ratingText.text = "${product.rating}"
        binding.ratingBar.rating = product.rating
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