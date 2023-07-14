package com.example.nanohealthtest.api

import com.example.nanohealthtest.model.data.DataProduct
import com.example.nanohealthtest.model.data.LoginData
import com.example.nanohealthtest.model.data.LoginResult
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST


interface ShopApi {
    @GET("products")
    fun getProducts(): Call<List<DataProduct>>

    @POST("auth/login")
    fun login(@Body loginData: LoginData): Call<LoginResult>
}