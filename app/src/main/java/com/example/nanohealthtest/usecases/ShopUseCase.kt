package com.example.nanohealthtest.usecases

import com.example.nanohealthtest.api.ShopApi
import com.example.nanohealthtest.mappers.ProductsMapper
import com.example.nanohealthtest.model.data.DataProduct
import com.example.nanohealthtest.model.data.LoginData
import com.example.nanohealthtest.model.domain.DomainProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

object ShopUseCase {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://fakestoreapi.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val shopApi = retrofit.create<ShopApi>()

    private var oldDataProducts: List<DataProduct>? = null
    private var oldDomainProduct: List<DomainProduct>? = null

    suspend fun fetchProducts(): List<DomainProduct> = withContext(Dispatchers.IO) {
        val call = shopApi.getProducts()
        val response = call.execute()
        if (!response.isSuccessful) {
            return@withContext emptyList()
        }
        val dataProducts = response.body() ?: return@withContext emptyList()

        val oldDataProductsConst = oldDataProducts
        val oldDomainProductsConst = oldDomainProduct
        if (dataProducts == oldDataProductsConst && oldDomainProductsConst != null) {
            return@withContext oldDomainProductsConst
        }

        oldDataProducts = dataProducts
        val domainProducts = dataProducts.map { ProductsMapper.dataToDomain(it) }
        oldDomainProduct = domainProducts
        return@withContext domainProducts
    }

    suspend fun login(loginData: LoginData): Boolean = withContext(Dispatchers.IO) {
        val call = shopApi.login(loginData)
        val response = call.execute()
        if (response.isSuccessful && response.body()?.token != null) {
            return@withContext true
        }
        return@withContext false
    }
}