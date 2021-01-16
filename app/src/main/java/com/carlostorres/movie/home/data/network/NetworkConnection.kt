package com.carlostorres.movie.home.data.network

import com.carlostorres.movie.utils.Environment.URL_BASE
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NetworkConnection {

    var RATED_SERVICE: RatedService? = null

    fun getNetworkConnection(): RatedService? {

        val interceptor = HttpLoggingInterceptor()

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        if ( RATED_SERVICE == null ) {
            val retrofit = Retrofit.Builder().baseUrl(URL_BASE)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()

            RATED_SERVICE = retrofit.create<RatedService>(RatedService::class.java)
        }

        return RATED_SERVICE
    }
}