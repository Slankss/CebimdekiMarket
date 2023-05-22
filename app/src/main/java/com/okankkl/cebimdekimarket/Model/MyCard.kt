package com.okankkl.cebimdekimarket.Model

import androidx.room.Dao
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("MyCard")
data class MyCard (

    var p_id : Int,
    var p_name : String,
    var p_price : Double,
    var quality : Int,
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0

)