package com.example.nanohealthtest.usecases

import com.example.nanohealthtest.api.ProductsApi
import com.example.nanohealthtest.mappers.ProductsMapper
import com.example.nanohealthtest.model.DomainProduct

class ProductsUseCase {
    companion object {
        suspend fun fetchProducts(): List<DomainProduct> {
            val dataProducts = ProductsApi.getProducts()
            return dataProducts.map { ProductsMapper.dataToDomain(it) }
        }
    }
}