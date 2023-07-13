package com.example.nanohealthtest.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nanohealthtest.databinding.FragmentProductsBinding
import com.example.nanohealthtest.model.Product
import com.example.nanohealthtest.ui.viewmodel.ProductsViewModel

class ProductsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FragmentProductsBinding.inflate(inflater)

        val products = mutableListOf<Product>()
        val productsAdapter = ProductsAdapter(products)
        binding.productsRecycler.adapter = productsAdapter
        binding.productsRecycler.layoutManager = LinearLayoutManager(activity)

        val productsViewModel by activityViewModels<ProductsViewModel>()
        productsViewModel.fetchData()
        productsViewModel.uiState.observe(viewLifecycleOwner) { newUiState ->
            products.clear()
            products.addAll(newUiState.products)
            productsAdapter.notifyDataSetChanged()
        }

        return binding.root
    }
}