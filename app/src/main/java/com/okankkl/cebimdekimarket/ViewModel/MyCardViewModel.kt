package com.okankkl.cebimdekimarket.ViewModel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okankkl.cebimdekimarket.Db.AppDatabase
import com.okankkl.cebimdekimarket.Model.MyCard
import com.okankkl.cebimdekimarket.Model.Product
import com.okankkl.cebimdekimarket.Repository.MyCardRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import java.util.concurrent.locks.Lock

class MyCardViewModel(context : Context) : ViewModel() {

    var myCardList = MutableLiveData<List<MyCard>>()
    private lateinit var repository: MyCardRepository

    init {
        viewModelScope.launch {
            val myCardDao = AppDatabase.getDatabase(context).myCardDao()
            repository = MyCardRepository(myCardDao)
            readCard()
        }
    }

    fun addCard(product : Product, quality : Int)  {

        viewModelScope.launch {

            var myCard : MyCard? = null

            var job1 = launch {
                myCard = repository.getMyCard(product.id)
                Log.w("JOB","JOB 1 STARTED")
            }
            job1.join()
            var job2 = launch {
                Log.w("JOB","JOB 2 STARTED")
                if(myCard != null){
                    myCard!!.quality += quality
                    repository.addCard(myCard!!)
                }
                else{
                    var card = MyCard(product.id,product.name,product.price,quality)
                    repository.addCard(card)
                }
                repository.realAllMyCard()
            }

        }
    }


    fun readCard(){
        viewModelScope.launch {
            myCardList.postValue(repository.realAllMyCard())
        }
    }

    fun deleteCard(myCard : MyCard){
        viewModelScope.launch {
            repository.deleteCard(myCard)
            readCard()
        }

    }

    fun increaseProduct(c_id : Int){
        viewModelScope.launch {
            repository.increaseProduct(c_id)
            readCard()
        }
    }

    fun decreaseProduct(c_id : Int){
        viewModelScope.launch {
            repository.decreaseProduct(c_id)
            readCard()
        }
    }



}