package com.okankkl.cebimdekimarket.Repository

import android.util.Log
import com.google.gson.GsonBuilder
import com.okankkl.cebimdekimarket.Dao.ProductDao
import com.okankkl.cebimdekimarket.Model.Product
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class ProductRepository(private var dao: ProductDao) {

    fun getAllProductsFromRoom() : List<Product>{
        return dao.getProductsFromRoom()
    }

    suspend fun insertProducts(productList : List<Product>){
        dao.insertAll(productList)
    }



}