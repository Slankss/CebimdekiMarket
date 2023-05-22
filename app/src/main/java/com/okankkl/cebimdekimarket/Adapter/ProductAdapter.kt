package com.okankkl.cebimdekimarket.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.okankkl.cebimdekimarket.Model.MyCard
import com.okankkl.cebimdekimarket.Model.Product
import com.okankkl.cebimdekimarket.databinding.ProductRowBinding
import com.squareup.picasso.Picasso

class ProductAdapter  : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    var productList = emptyList<Product>()
    var addCardClick : (Product,Int) -> Unit = { product : Product, quality : Int -> }

    fun setData(productList : List<Product>){
        this.productList = productList
    }

    class ViewHolder(val binding : ProductRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductRowBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var current = productList[position]
        var quality = 1

        holder.binding.apply {
            txtProductName.text = current.name
            val priceText = current.price.toString() +" ₺"
            txtProductPrice.text = priceText
            Picasso.get()
                .load(current.image_url)
                .resize(400,350)
                .centerCrop()
                .into(imgProduct)

            btnAddCard.setOnClickListener {
                addCardClick(current,quality)
                quality = 1
                txtProductQuality.setText(quality.toString())
            }

            btnIncreaseQuality.setOnClickListener {
                quality++
                txtProductQuality.setText(quality.toString())
            }
            btnDecreaseQuality.setOnClickListener {
                if(quality != 1){
                    quality--
                    txtProductQuality.setText(quality.toString())
                }
            }

        }


    }
}