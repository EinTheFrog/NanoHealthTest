package com.example.nanohealthtest.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nanohealthtest.R
import com.example.nanohealthtest.model.Product

class ProductsAdapter(private val productList: List<Product>):
    RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView: TextView

        init {
            nameView = view.findViewById(R.id.product_name_text)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_product, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.nameView.text = productList[position].name
    }

    override fun getItemCount(): Int {
        return productList.size
    }
}