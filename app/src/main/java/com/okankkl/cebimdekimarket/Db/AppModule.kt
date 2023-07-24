package com.okankkl.cebimdekimarket.Db

import android.content.Context
import androidx.room.Room
import com.okankkl.cebimdekimarket.Dao.ProductRetrofitDao
import com.okankkl.cebimdekimarket.Db.util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule
{
    
    @Singleton
    @Provides
    fun injectRoomDatabase(
            @ApplicationContext context : Context) = Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "application_database"
            ).build()
    
    @Singleton
    @Provides
    fun injectDao(database : AppDatabase) = database.productDao()
    
    
    @Singleton
    @Provides
    fun injectRetrofit() : ProductRetrofitDao {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ProductRetrofitDao::class.java)
    }
    
}