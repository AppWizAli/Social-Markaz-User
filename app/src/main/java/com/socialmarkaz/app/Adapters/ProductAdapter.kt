package com.socialmarkaz.app.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.socialmarkaz.app.Constants
import com.socialmarkaz.app.Models.Product
import com.socialmarkaz.app.R
import com.socialmarkaz.app.databinding.RecProductBinding

class ProductAdapter (val data: List<Product>, private val context: Context,
                      private val itemClickListener: OnItemClickListener) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {


    var constant = Constants()

    interface OnItemClickListener {
        fun onItemClick(product: Product)
        fun onDeleteClick(product: Product)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecProductBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    inner class ViewHolder(val productBinding: RecProductBinding) :
        RecyclerView.ViewHolder(productBinding.root) {

        fun bind(productModel: Product) {

            val context=productBinding.root.context
            productBinding.productName.text = productModel.productName
            productBinding.price.text = productModel.price
            productBinding.sellerName.text = productModel.selerName
            Glide.with(context)
                .load(productModel.productPhoto)
                .centerCrop()
                .placeholder(R.drawable.ic_launcher_background)
                .into(productBinding.productImg)
            productBinding.layproduct.setOnClickListener {
                itemClickListener.onItemClick(productModel)
            }

}



    }
}