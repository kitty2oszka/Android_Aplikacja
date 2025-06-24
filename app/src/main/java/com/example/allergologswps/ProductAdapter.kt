package com.example.allergologswps

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.net.URL
import java.util.concurrent.Executors

class ProductAdapter(private val products: List<ProductApi>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {
    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val productImage: ImageView = itemView.findViewById(R.id.productImage)
        val productId: TextView = itemView.findViewById(R.id.productId)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.productId.text = product.id
        holder.productImage.setImageBitmap(null)
        product.imageUrl?.let { url ->
            Executors.newSingleThreadExecutor().execute {
                try {
                    val input = URL(url).openStream()
                    val bitmap = BitmapFactory.decodeStream(input)
                    Handler(Looper.getMainLooper()).post {
                        holder.productImage.setImageBitmap(bitmap)
                    }
                } catch (e: Exception) {
                    // ignore, show no image
                }
            }
        }
    }

    override fun getItemCount(): Int = products.size
}
