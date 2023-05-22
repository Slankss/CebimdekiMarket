package com.okankkl.cebimdekimarket.Dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.okankkl.cebimdekimarket.Model.Product
import retrofit2.Response
import retrofit2.http.GET


@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(productList : List<Product>)

    @Query("SELECT * FROM Product")
    fun getProductsFromRoom() : List<Product>



}