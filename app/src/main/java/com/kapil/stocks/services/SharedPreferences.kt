package com.kapil.stocks.services

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kapil.stocks.constants.Constants
import com.kapil.stocks.data.model.WatchList

object SharedPreferences {

    fun saveWatchlistData(context: Context, data: List<WatchList>){
        val sharedPreferences = context.getSharedPreferences(Constants.WATCHLIST_PREF_FILE, MODE_PRIVATE)
        val editor = sharedPreferences.edit()

        try {
            val gson = Gson()
            val stringifiedData = gson.toJson(data)
            editor.putString(Constants.WATCHLIST_DATA, stringifiedData)
            editor.apply()
        } catch (e: Exception){
            Log.d(Constants.TAG, "watchlist save error ${e.message}")
        }
    }

    fun readWatchlistData(context: Context): List<WatchList> {
        val sharedPreferences = context.getSharedPreferences(Constants.WATCHLIST_PREF_FILE, MODE_PRIVATE)
        val stringData = sharedPreferences.getString(Constants.WATCHLIST_DATA, "")

        try {
            val gson = Gson()
            val type = object : TypeToken<List<WatchList>>() {}.type
            val data: List<WatchList>? = if (stringData != null) {
                gson.fromJson(stringData, type)
            } else {
                emptyList()
            }

            return data ?: emptyList()
        }catch (e: Exception){
            return emptyList()
        }
    }
}