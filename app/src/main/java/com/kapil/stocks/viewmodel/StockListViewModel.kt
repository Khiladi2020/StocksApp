package com.kapil.stocks.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kapil.stocks.data.fallback.MarketSummaryFallback
import com.kapil.stocks.data.model.Stock
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

enum class STOCKS_LIST_TYPE(type: String) {
    GAINERS("GAINERS"),
    LOSERS("LOSERS"),
    ALL("ALL")

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
                }
            }
        }

    }
}
