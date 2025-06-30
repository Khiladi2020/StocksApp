package com.kapil.stocks.data.model

import java.io.Serializable

data class StockMarketSummary(
    val metadata: String,
    val lastUpdated: String,
    val topGainers: List<Stock>,
    val topLosers: List<Stock>,
    val mostActivelyTraded: List<Stock>
) : Serializable
