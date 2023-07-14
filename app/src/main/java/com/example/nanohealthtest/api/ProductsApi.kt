package com.example.nanohealthtest.api

import com.example.nanohealthtest.model.data.DataProduct
import org.json.JSONObject

object ProductsApi {
    suspend fun getProducts(): List<DataProduct> {
        val result = mutableListOf<DataProduct>()
        val json = getProductsFromServer()
        val jsonArray = json.getJSONArray("products")
        for (ind in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.optJSONObject(ind)
            val product = convertJsonToProduct(jsonObject)
            result.add(product)
        }
        return result
    }

    private fun convertJsonToProduct(jsonObject: JSONObject): DataProduct = DataProduct(
        id = jsonObject.getLong("id"),
        name = jsonObject.getString("name"),
        description = jsonObject.getString("description").toString(),
        price = jsonObject.getInt("price"),
        reviewsAmount = jsonObject.getInt("reviewsAmount"),
        rating = jsonObject.getInt("rating"),
        imageUrl = jsonObject.getString("imageUrl")
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
                        "rating": 420,
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