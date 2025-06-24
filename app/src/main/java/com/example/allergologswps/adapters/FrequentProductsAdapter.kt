package com.example.allergologswps.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.allergologswps.databinding.ItemProductBinding
import com.example.allergologswps.models.FrequentProduct

class FrequentProductsAdapter : ListAdapter<FrequentProduct, FrequentProductsAdapter.ViewHolder>(DIFF_CALLBACK) {

    class ViewHolder(val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        Glide.with(holder.itemView.context).load(item.imageUrl).into(holder.binding.productImage)
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<FrequentProduct>() {
            override fun areItemsTheSame(oldItem: FrequentProduct, newItem: FrequentProduct): Boolean {
                return oldItem.imageUrl == newItem.imageUrl
            }

            override fun areContentsTheSame(oldItem: FrequentProduct, newItem: FrequentProduct): Boolean {
                return oldItem == newItem
            }
        }
    }
}
