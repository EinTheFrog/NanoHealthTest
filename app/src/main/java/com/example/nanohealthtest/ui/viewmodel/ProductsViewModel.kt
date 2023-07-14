package com.example.nanohealthtest.ui.viewmodel

import android.content.res.Resources.NotFoundException
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nanohealthtest.model.domain.DomainProduct
import com.example.nanohealthtest.usecases.ProductsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsViewModel: ViewModel() {
    data class UiState(
        val productList: List<DomainProduct>,
        val isLoading: Boolean
    )

    private val _uiState = MutableLiveData(UiState(emptyList(), true))
    val uiState: LiveData<UiState> = _uiState

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.postValue(_uiState.value?.copy(isLoading = true))
            val newProducts = ProductsUseCase.fetchProducts()
            _uiState.postValue(_uiState.value?.copy(productList = newProducts, isLoading = false))
        }
    }

    fun getProduct(productId: Int): DomainProduct {
        val productList = uiState.value?.productList ?: throw NotFoundException("Product list doesn't exist")
        for (product in productList) {
            if (product.id == productId) return product
        }
        throw NotFoundException("Could not find product by its id")
    }
}