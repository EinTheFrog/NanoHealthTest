package com.example.nanohealthtest.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nanohealthtest.model.DomainProduct
import com.example.nanohealthtest.usecases.ProductsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductsViewModel: ViewModel() {
    data class UiState(
        val domainProducts: List<DomainProduct>
    )

    private val _uiState = MutableLiveData(UiState(emptyList()))
    val uiState: LiveData<UiState> = _uiState

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            val newProducts = ProductsUseCase.fetchProducts()
            _uiState.postValue(_uiState.value?.copy(domainProducts = newProducts))
        }
    }
}