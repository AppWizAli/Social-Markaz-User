package com.socialmarkaz.app.Adapters

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.socialmarkaz.app.Constants
import com.socialmarkaz.app.Models.Product
import com.socialmarkaz.app.databinding.RecProductBinding
import java.text.SimpleDateFormat
import java.util.Locale

class RecomendedProductAdapter ( val data: List<Product>) : RecyclerView.Adapter<RecomendedProductAdapter.ViewHolder>() {


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

            productBinding.productName.text = productModel.productName
            productBinding.price.text = productModel.price
            productBinding.sellerName.text = productModel.selerName



        }
    }
}