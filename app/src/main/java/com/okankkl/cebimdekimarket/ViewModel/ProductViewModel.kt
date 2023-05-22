package com.okankkl.cebimdekimarket.ViewModel

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.okankkl.cebimdekimarket.Dao.ProductDao
import com.okankkl.cebimdekimarket.Dao.ProductRetrofitDao
import com.okankkl.cebimdekimarket.Db.AppDatabase
import com.okankkl.cebimdekimarket.Model.MyCard
import com.okankkl.cebimdekimarket.Model.Product
import com.okankkl.cebimdekimarket.Model.Sorted
import com.okankkl.cebimdekimarket.Repository.MyCardRepository
import com.okankkl.cebimdekimarket.Repository.ProductRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.Calendar

class ProductViewModel(val context : Context) : ViewModel() {

    private lateinit var repository: ProductRepository
    var productList = MutableLiveData<List<Product>>()
    val sharedPref = context.getSharedPreferences("time",Context.MODE_PRIVATE)
    val editor = sharedPref.edit()

    init {
        viewModelScope.launch {
            var dao = AppDatabase.getDatabase(context).productDao()
            repository = ProductRepository(dao)

            var date : String? = null

            var job1 = launch {
                date = sharedPref.getString("updatedDate",null)
            }
            job1.join()

            val job2 = launch {
                if(date == null){
                    getProductsFromInternet()
                }
                else{

                    var dateTime = stringToLocalDateTime(date!!.toString())
                    val currentTime = LocalDateTime.now()
                    var minute = dateTime.until(currentTime,ChronoUnit.MINUTES)

                    if(minute < 1){
                        getProductsFromRoom()
                    }
                    else{
                        getProductsFromInternet()
                    }
                }
            }
        }
    }

    fun getProductsFromInternet(){

        val BASE_URL = "https://raw.githubusercontent.com/"
        val currentTime = LocalDateTime.now()

        viewModelScope.launch {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(ProductRetrofitDao::class.java)

            CoroutineScope(Dispatchers.IO).launch {

                val response = service.getProducts()

                withContext(Dispatchers.Main){
                    if(response.isSuccessful){
                        response.body()?.let { list ->
                            productList.postValue(list)
                            repository.insertProducts(list)
                        }
                        editor.putString("updatedDate",currentTime.toString())
                        editor.commit()
                    }
                }


            }
            Toast.makeText(context,"GET FROM THE INTERNET",Toast.LENGTH_SHORT).show()
        }
    }


    fun getProductsFromRoom(){
        viewModelScope.launch(Dispatchers.IO) {
            productList.postValue(repository.getAllProductsFromRoom())

        }
        Toast.makeText(context,"GET FROM THE ROOM",Toast.LENGTH_SHORT).show()
    }

    fun stringToLocalDateTime(dateString : String) : LocalDateTime{
        return LocalDateTime.parse(dateString)
    }



}