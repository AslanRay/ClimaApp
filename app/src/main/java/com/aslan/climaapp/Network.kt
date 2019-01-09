package com.aslan.climaapp

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity

class Network {
    companion object {
        fun hayRed(actitvity:AppCompatActivity):Boolean {

            val connecitivityManager = actitvity.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connecitivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }
}