package com.example.nanohealthtest.ui

import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.nanohealthtest.R
import com.example.nanohealthtest.databinding.FragmentProductsBinding
import com.example.nanohealthtest.model.domain.DomainProduct
import com.example.nanohealthtest.ui.viewmodel.ProductsViewModel

class ProductsFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        val binding = FragmentProductsBinding.inflate(inflater)

        val productList = mutableListOf<DomainProduct>()
        val productsAdapter = ProductsAdapter(productList, resources, this::navigateToProductDetails)
        binding.productsRecycler.adapter = productsAdapter
        binding.productsRecycler.layoutManager = LinearLayoutManager(activity)

        val productsViewModel by activityViewModels<ProductsViewModel>()
        productsViewModel.fetchData()
        setUiStateObserver(binding, productsViewModel, productList, productsAdapter)

        return binding.root
    }

    private fun setUiStateObserver(
        binding: FragmentProductsBinding,
        productsViewModel: ProductsViewModel,
        productList: MutableList<DomainProduct>,
        productsAdapter: ProductsAdapter
    ) {
        productsViewModel.uiState.observe(viewLifecycleOwner) { newUiState ->
            binding.progressBar.visibility = if (newUiState.isLoading) View.VISIBLE else View.GONE
            if (productList == newUiState.productList) return@observe
            productList.clear()
            productList.addAll(newUiState.productList)
            productsAdapter.notifyDataSetChanged()
        }
    }

    private fun navigateToProductDetails(productId: Long) {
        val navController = findNavController()
        val action = ProductsFragmentDirections.actionProductsFragmentToDetailsFragment(productId)
        navController.navigate(action)
    }
}