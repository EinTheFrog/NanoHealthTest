package com.example.nanohealthtest.usecases

import com.example.nanohealthtest.api.ShopApi
import com.example.nanohealthtest.mappers.ProductsMapper
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

    suspend fun fetchProducts(): List<DomainProduct> = withContext(Dispatchers.IO) {
        val call = shopApi.getProducts()
        val response = call.execute()
        if (!response.isSuccessful) {
            return@withContext emptyList()
        }
        val dataProducts = response.body() ?: return@withContext emptyList()
        return@withContext dataProducts.map { ProductsMapper.dataToDomain(it) }
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