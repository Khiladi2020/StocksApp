package com.kapil.stocks.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    val baseUrl = "https://www.alphavantage.co"
//    val baseUrl = "http://10.0.2.2:3000"
    private var apiInstance: Retrofit? = null;

    fun getInstance(): Retrofit {
        if(apiInstance != null) return apiInstance as Retrofit;
        apiInstance =  Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            // we need to add converter factory to
            // convert JSON object to Java object
            .build()
        return apiInstance as Retrofit
    }

    fun getStocksApi(): StocksApi {
        return getInstance().create(StocksApi::class.java)
    }
}