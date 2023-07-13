package com.example.nanohealthtest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nanohealthtest.R
import com.example.nanohealthtest.model.domain.DomainProduct

class ProductsAdapter(private val productList: List<DomainProduct>):
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView
        val descriptionView: TextView
        val priceView: TextView
        val ratingView: TextView
        val imageView: ImageView

        init {
            nameView = view.findViewById(R.id.product_name_text)
            descriptionView = view.findViewById(R.id.product_description_text)
            priceView = view.findViewById(R.id.price_text)
            ratingView = view.findViewById(R.id.rating_text)
            imageView = view.findViewById(R.id.product_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_product, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameView.text = productList[position].name
        holder.descriptionView.text = productList[position].description
        val priceInAED: Double = productList[position].price / 100.0
        holder.priceView.text = "$priceInAED AED"
        val rangedRating: Double = productList[position].rating / 100.0
        holder.ratingView.text = "$rangedRating"
        holder.imageView.setImageBitmap(productList[position].image)
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}