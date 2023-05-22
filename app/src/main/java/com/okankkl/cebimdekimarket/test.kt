package com.okankkl.cebimdekimarket

import android.provider.Settings.Global
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.*

fun main(){


    GlobalScope.launch {
        task1()
        task2()
    }
    Thread.sleep(2000L)

}

suspend fun task1(){
    delay(2000L)
    println("Şampiyon")

}

suspend fun task2(){
    delay(1000L)
    println("Galatasaray")
}