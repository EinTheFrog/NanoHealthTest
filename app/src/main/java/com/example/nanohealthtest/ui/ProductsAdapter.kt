package com.example.nanohealthtest.ui

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nanohealthtest.R
import com.example.nanohealthtest.model.domain.DomainProduct
import com.squareup.picasso.Picasso

class ProductsAdapter(
    private val productList: List<DomainProduct>,
    private val resources: Resources,
    private val onProductClick: (Int) -> Unit
): RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView
        val descriptionView: TextView
        val priceView: TextView
        val ratingView: RatingBar
        val imageView: ImageView
        val clickView: ImageView

        init {
            nameView = view.findViewById(R.id.product_name_text)
            descriptionView = view.findViewById(R.id.product_description_text)
            priceView = view.findViewById(R.id.price_text)
            ratingView = view.findViewById(R.id.rating_bar)
            imageView = view.findViewById(R.id.product_image)
            clickView = view.findViewById(R.id.click_area_image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_product, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = productList[position]

        holder.nameView.text = product.name
        holder.descriptionView.text = product.description
        holder.priceView.text = resources.getString(R.string.price_in_aed, product.price)
        holder.ratingView.rating = product.rating
        Picasso.get().load(product.imageUrl).fit().centerCrop().into(holder.imageView)

        holder.clickView.setOnClickListener {
            onProductClick(product.id)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}