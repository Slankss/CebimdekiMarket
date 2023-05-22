package com.okankkl.cebimdekimarket.Dao

import com.okankkl.cebimdekimarket.Model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductRetrofitDao {

    @GET("Slankss/YemekSepetiClone-Product/master/Products.json")
    suspend fun getProducts() : Response<List<Product>>
}