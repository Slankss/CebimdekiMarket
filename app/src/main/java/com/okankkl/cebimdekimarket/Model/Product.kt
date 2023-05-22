package com.okankkl.cebimdekimarket.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable


@Entity("Product")
data class Product (

    @PrimaryKey
    @SerializedName("id")
    var id : Int,

    @SerializedName("name")
    @Expose
    var name : String,

    @SerializedName("price")
    @Expose
    var price : Double,

    @SerializedName("image_url")
    @Expose
    var image_url : String


        )