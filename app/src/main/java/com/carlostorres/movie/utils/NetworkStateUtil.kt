package com.carlostorres.movie.utils

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build

class NetworkStateUtil(context: Context) {

    private var nsContext = context
    private lateinit var networkCallback: ConnectivityManager.NetworkCallback
    lateinit var status: ( (isAvailable: Boolean, type: CoonectionType?) -> Unit )

    @Suppress("DEPRECATION")
    fun register() {
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ) {
            val connectivityManager = nsContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if ( connectivityManager.activeNetwork == null ) {
                status(false, null)
            }

            networkCallback = object : ConnectivityManager.NetworkCallback() {
                override fun onLost(network: Network) {
                    super.onLost(network)
                    status(false, null)
                }

                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities
                ) {
                    super.onCapabilitiesChanged(network, networkCapabilities)
                    when {
                        networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> {
                            status(true, CoonectionType.Wifi)
                        }
                        else -> {
                            status(true, CoonectionType.Cellular)
                        }
                    }
                }
            }
            connectivityManager.registerDefaultNetworkCallback(networkCallback)
        } else {
            val intentFilter = IntentFilter()
            intentFilter.addAction("android.net.com.CONNECTIVITY_CHANGE")
            nsContext.registerReceiver( networkChangeReceiver, intentFilter)
        }
    }

    fun unregister() {
        if ( Build.VERSION.SDK_INT >= Build.VERSION_CODES.P ) {
            val connectivityManager = nsContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            connectivityManager.unregisterNetworkCallback(networkCallback)
        } else {
            nsContext.unregisterReceiver(networkChangeReceiver)
        }
    }

    @Suppress("DEPRECATION")
    private val networkChangeReceiver: BroadcastReceiver = object : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val connectivityManager = nsContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo

            if ( activeNetworkInfo != null ) {
                when(activeNetworkInfo.type) {
                    ConnectivityManager.TYPE_WIFI -> {
                        status(true, CoonectionType.Wifi)
                    }
                    else -> {
                        status(true, CoonectionType.Cellular)
                    }
                }
            } else {
                status(false, null)
            }
        }
    }
}