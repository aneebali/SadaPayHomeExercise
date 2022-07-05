package com.aneeb.sadapayhomeexercize.util

import android.content.Context
import android.net.ConnectivityManager

object CommonMethods {

    fun isNetworkConnected(context: Context?): Boolean {
        if (context != null) {
            val cm = (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
            val ni = cm.activeNetworkInfo
            return ni != null
        }
        return false
    }
}