package com.example.nanohealthtest.api

import com.example.nanohealthtest.model.data.DataProduct
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import org.jsoup.Jsoup

object ProductsApi {
    suspend fun getProducts(): List<DataProduct> = withContext(Dispatchers.IO) {
        val result = mutableListOf<DataProduct>()

        val doc = Jsoup.connect("https://fakestoreapi.com/products").ignoreContentType(true).get()
        val json = doc.select("body").html()
        val jsonArray = JSONArray(json)
        for (ind in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.optJSONObject(ind)
            val product = convertJsonToProduct(jsonObject)
            result.add(product)
        }
        return@withContext result
    }

    private fun convertJsonToProduct(jsonObject: JSONObject): DataProduct = DataProduct(
        id = jsonObject.getInt("id"),
        name = jsonObject.getString("title"),
        description = jsonObject.getString("description").toString(),
        price = jsonObject.getString("price").toFloat(),
        reviewsAmount = jsonObject.getJSONObject("rating").getInt("count"),
        rating = jsonObject.getJSONObject("rating").getString("rate").toFloat(),
        imageUrl = jsonObject.getString("image")
    )

    suspend private fun getProductsFromServer(): JSONObject {
        val jsonString = """
            {
                "products": [
                    {
                        "id": 0,
                        "name": "Shoes",
                        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna",
                        "price": 39999,
                        "reviewsAmount": 110,
                        "rating": 380,
                        "imageUrl": "https://fabram.com/wp-content/uploads/2021/06/Luxury-Sneakers-cleaning-in-Dubai.jpg"
                    },
                    {
                        "id": 1,
                        "name": "T-Shirt",
                        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna",
                        "price": 2999,
                        "reviewsAmount": 2403,
                        "rating": 431,
                        "imageUrl": "https://c1.wallpaperflare.com/preview/414/396/979/person-outdoor-pad-brace.jpg"
                    },
                    {
                        "id": 2,
                        "name": "Cap",
                        "description": "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna",
                        "price": 39999,
                        "reviewsAmount": 1830,
                        "rating": 499,
                        "imageUrl": "https://foxerz.com/wp-content/uploads/2023/02/Dubai1_8db3d575-43d3-4481-b433-896a3a32c890.png"
                    }
                ]
            }
        """.trimIndent()

        return JSONObject(jsonString)
    }
}