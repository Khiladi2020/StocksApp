package com.kapil.stocks.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kapil.stocks.constants.Constants
import com.kapil.stocks.data.fallback.AllStockInfoFallback
import com.kapil.stocks.data.fallback.StockDetailFallback
import com.kapil.stocks.data.model.StockDetails
import com.kapil.stocks.network.ApiService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StockViewModel : ViewModel() {
    // private stuff
    private val _stockdetails: MutableStateFlow<StockDetails?> = MutableStateFlow(null)
    private val _isLoading: MutableStateFlow<Boolean?> = MutableStateFlow(false)
    private val _authStatus: MutableStateFlow<Boolean> = MutableStateFlow(false)

    // Public
    val stockDetails: StateFlow<StockDetails?> = _stockdetails.asStateFlow();
    val isLoading: StateFlow<Boolean?> = _isLoading.asStateFlow();

    fun fetchStockDetails(stockName: String) {
        Log.d(Constants.TAG, "fetch stock details called ${stockName}")
        viewModelScope.launch {
            try {
                _isLoading.value = true
                val stockApiInstance = ApiService.getStocksApi()
                val response = stockApiInstance.getStockDetails(stockName, "AII507GDUFZTDWJ3");
                if(response.body()?.Name !== null && response.body()?.Description !== null){
                    _stockdetails.emit(response.body())
                } else {
                    throw Exception("All data is null")
                }
                Log.d(Constants.TAG, "API response ${response} ${response.body()}")
            } catch (e: Exception) {
                Log.d(Constants.TAG, "stock details API failed")
                // Serve fallback data
                val data = AllStockInfoFallback.allStocks.get(stockName)
                _stockdetails.emit(data)
            } finally {
                _isLoading.value = false
            }

        }
    }
}