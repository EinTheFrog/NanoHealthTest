package com.example.nanohealthtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nanohealthtest.api.ProductsApi
import com.example.nanohealthtest.model.Product
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsViewModel: ViewModel() {
    data class UiState(
        val products: List<Product>
    )

    private val _uiState = MutableLiveData(UiState(emptyList()))
    val uiState: LiveData<UiState> = _uiState

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            val newProducts = ProductsApi.getProducts()
            _uiState.postValue(_uiState.value?.copy(products = newProducts))
        }
    }
}