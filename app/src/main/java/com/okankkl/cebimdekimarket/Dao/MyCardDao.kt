package com.okankkl.cebimdekimarket.Dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.okankkl.cebimdekimarket.Model.MyCard

@Dao
interface MyCardDao {


    @Query("SELECT * FROM MyCard")
    suspend fun readAllCard() : List<MyCard>

    @Query("SELECT * FROM MyCard WHERE p_id = :p_id")
    suspend fun  getCard(p_id : Int) : MyCard

    @Upsert
    suspend fun addCard(myCard : MyCard)

    @Delete
    suspend fun deleteCard(myCard: MyCard)

    @Query("UPDATE MyCard SET quality = quality + 1 WHERE id = :c_id")
    suspend fun increaseProduct(c_id : Int)

    @Query("UPDATE MyCard SET quality = quality - 1 WHERE id = :c_id")
    suspend fun decreaseProduct(c_id : Int)


}