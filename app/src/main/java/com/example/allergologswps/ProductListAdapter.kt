package com.example.allergologswps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.allergologswps.api.ProductDetail

class ProductListAdapter(private val products: List<ProductDetail>) : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameText: TextView = view.findViewById(android.R.id.text1)
        val imageView: ImageView = view.findViewById(android.R.id.icon)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.activity_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = products[position]
        holder.nameText.text = product.product_name ?: "Brak nazwy"
        if (!product.image_url.isNullOrEmpty()) {
            Glide.with(holder.itemView)
                .load(product.image_url)
                .into(holder.imageView)
        } else {
            holder.imageView.setImageResource(android.R.drawable.ic_menu_report_image)
        }
    }

    override fun getItemCount() = products.size
}
