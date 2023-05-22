package com.okankkl.cebimdekimarket.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.okankkl.cebimdekimarket.Model.MyCard
import com.okankkl.cebimdekimarket.databinding.MyCardRowBinding

class MyCardAdapter : RecyclerView.Adapter<MyCardAdapter.ViewHolder>() {

    var myCardList = emptyList<MyCard>()

    var decreaseClick : (Int) -> Unit = {}
    var increaseClick : (Int) -> Unit = {}
    var deleteClick : (MyCard) -> Unit = {}

    fun setData(myCardList : List<MyCard>){
        this.myCardList = myCardList
    }

    class ViewHolder(val binding : MyCardRowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = MyCardRowBinding.inflate(layoutInflater,parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return myCardList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        var current = myCardList[position]
        var quality = current.quality


        holder.binding.apply {

            txtProductName.text = current.p_name
            txtProductQuality.text = current.quality.toString()
            btnDeleteProduct.setOnClickListener {
                deleteClick(current)
            }

            btnIncreaseQuality.setOnClickListener {
                quality++
                txtProductQuality.text = quality.toString()
                increaseClick(current.id)
            }

            btnDecreaseQuality.setOnClickListener {
                if(quality != 1){
                    quality--
                    txtProductQuality.text = quality.toString()
                    decreaseClick(current.id)
                }
            }



        }

    }
}