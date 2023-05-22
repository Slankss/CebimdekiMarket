package com.okankkl.cebimdekimarket.Repository

import com.okankkl.cebimdekimarket.Dao.MyCardDao
import com.okankkl.cebimdekimarket.Model.MyCard

class MyCardRepository(private val myCardDao : MyCardDao) {

    suspend fun realAllMyCard() : List<MyCard> {
        return myCardDao.readAllCard()
    }

    suspend fun addCard(myCard : MyCard) {
        myCardDao.addCard(myCard)
    }

    suspend fun deleteCard(myCard: MyCard){
        myCardDao.deleteCard(myCard)
    }

    suspend fun getMyCard(p_id : Int) : MyCard{
        return myCardDao.getCard(p_id)
    }

    suspend fun increaseProduct(c_id : Int){
        myCardDao.increaseProduct(c_id)
    }

    suspend fun decreaseProduct(c_id : Int){
        myCardDao.decreaseProduct(c_id)
    }


}