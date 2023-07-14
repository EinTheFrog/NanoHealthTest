package com.example.nanohealthtest.api

import com.example.nanohealthtest.model.data.DataProduct

object ProductsApi {
    suspend fun getProducts(): List<DataProduct> {
        return listOf(
            DataProduct(
                0,
                "Shoes",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna",
                39999,
                110,
                420,
                "https://fabram.com/wp-content/uploads/2021/06/Luxury-Sneakers-cleaning-in-Dubai.jpg"
            ),
            DataProduct(
                1,
                "T-Shirt",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna",
                2999,
                2403,
                431,
                "https://c1.wallpaperflare.com/preview/414/396/979/person-outdoor-pad-brace.jpg"
            ),
            DataProduct(
                2,
                "Hat",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna",
                1299,
                1830,
                499,
                "https://foxerz.com/wp-content/uploads/2023/02/Dubai1_8db3d575-43d3-4481-b433-896a3a32c890.png"
            )
        )
    }
}