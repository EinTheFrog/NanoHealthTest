package com.example.nanohealthtest.api

import com.example.nanohealthtest.model.Product

class ProductsApi {
    companion object {
        suspend fun getProducts(): List<Product> {
            return listOf(
                Product(0, "Shoes", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna"),
                Product(1, "T-Shirt", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna"),
                Product(2, "Hat", "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna")
            )
        }
    }
}