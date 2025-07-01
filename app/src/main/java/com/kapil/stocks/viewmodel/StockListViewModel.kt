package com.kapil.stocks.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kapil.stocks.data.fallback.MarketSummaryFallback
import com.kapil.stocks.data.model.Stock
import com.kapil.stocks.services.SharedPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import kotlin.random.Random

enum class STOCKS_LIST_TYPE(type: String) {
    GAINERS("GAINERS"),
    LOSERS("LOSERS"),
    ALL("ALL"),
    WATCHLIST("WATCHLIST")
}

class StockListViewModel : ViewModel() {

    // Private
    private val _stockList: MutableStateFlow<List<Stock>> = MutableStateFlow(emptyList())

    // Public
    val stockDetails: StateFlow<List<Stock>> = _stockList.asStateFlow();


    fun fetchStocksList(dataType: STOCKS_LIST_TYPE) {
        viewModelScope.launch {
            if (false) {
                // API call code
            } else {
                // Fallback to serve local data
                when (dataType) {
                    STOCKS_LIST_TYPE.GAINERS -> _stockList.emit(MarketSummaryFallback.marketSummaryData.topGainers)
                    STOCKS_LIST_TYPE.ALL -> _stockList.emit(MarketSummaryFallback.marketSummaryData.topGainers)
                    STOCKS_LIST_TYPE.LOSERS -> _stockList.emit(MarketSummaryFallback.marketSummaryData.topLosers)
                    STOCKS_LIST_TYPE.WATCHLIST -> TODO()
                }
            }
        }
    }

    fun fetchWatchList(context: Context){
        viewModelScope.launch {
            val watchListData = SharedPreferences.readWatchlistData(context)
            if(watchListData.size > 0){
                val data = watchListData[0].stocks.map { it ->
                    Stock(
                        name = it.Symbol,
                        price = it.PriceToBookRatio,
                        isGainer = false,
                        changePercent = Random.nextDouble()
                    )
                }
                _stockList.emit(data)
            }
        }

    }

    fun fetchTopGainersLosers(dataType: STOCKS_LIST_TYPE): Flow<List<Stock>> {
        return flow {
            if (false) {
                // API call code
            } else {
                // Fallback to serve local data
                when (dataType) {
                    STOCKS_LIST_TYPE.GAINERS -> emit(MarketSummaryFallback.marketSummaryData.topGainers)
                    STOCKS_LIST_TYPE.ALL -> emit(MarketSummaryFallback.marketSummaryData.topGainers)
                    STOCKS_LIST_TYPE.LOSERS -> emit(MarketSummaryFallback.marketSummaryData.topLosers)
                    STOCKS_LIST_TYPE.WATCHLIST -> TODO()
                }
            }
        }
    }
}
