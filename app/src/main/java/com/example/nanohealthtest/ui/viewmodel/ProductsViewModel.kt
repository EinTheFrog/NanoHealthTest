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
        val productList: List<DomainProduct>
    )

    private val _uiState = MutableLiveData(UiState(emptyList()))
    val uiState: LiveData<UiState> = _uiState

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            val newProducts = ProductsUseCase.fetchProducts()
            _uiState.postValue(_uiState.value?.copy(productList = newProducts))
        }
    }

    fun getProduct(productId: Long): DomainProduct {
        val productList = uiState.value?.productList ?: throw NotFoundException("Product list doesn't exist")
        for (product in productList) {
            if (product.id == productId) return product
        }
        throw NotFoundException("Could not find product by its id")
    }
}