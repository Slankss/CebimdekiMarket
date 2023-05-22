package com.okankkl.cebimdekimarket.Db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.okankkl.cebimdekimarket.Dao.MyCardDao
import com.okankkl.cebimdekimarket.Dao.ProductDao
import com.okankkl.cebimdekimarket.Model.MyCard
import com.okankkl.cebimdekimarket.Model.Product

@Database(
    entities = [MyCard::class, Product::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun myCardDao() : MyCardDao
    abstract fun productDao() : ProductDao

    companion object{

        private var INSTANCE : AppDatabase? = null

        fun getDatabase(context : Context) : AppDatabase {

            var tmpInstance = INSTANCE

            if(tmpInstance != null){
                return tmpInstance
            }

            synchronized(this){

                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database"
                ).build()
                INSTANCE = instance
                return instance
            }

        }



    }

}