package com.example.nanohealthtest.ui

import android.content.res.Resources
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nanohealthtest.R
import com.example.nanohealthtest.model.domain.DomainProduct

class ProductsAdapter(
    private val productList: List<DomainProduct>,
    private val resources: Resources,
    private val onProductClick: (Long) -> Unit
): RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView
        val descriptionView: TextView
        val priceView: TextView
        val imageView: ImageView
        val clickView: ImageView

        init {
            nameView = view.findViewById(R.id.product_name_text)
            descriptionView = view.findViewById(R.id.product_description_text)
            priceView = view.findViewById(R.id.price_text)
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
        val priceInAED: Double = product.price / 100.0
        holder.priceView.text = "$priceInAED AED"
        holder.imageView.background = BitmapDrawable(resources, product.image)
        holder.clickView.setOnClickListener {
            onProductClick(product.id)
        }
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}