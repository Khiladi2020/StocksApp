package com.kapil.stocks.data.fallback

import com.kapil.stocks.data.model.Stock
import com.kapil.stocks.data.model.StockMarketSummary

object MarketSummaryFallback {
    val marketSummaryData = StockMarketSummary(
        metadata = "Top gainers, losers, and most actively traded US tickers",
        lastUpdated = "2025-06-27 16:15:59 US/Eastern",

        topGainers = listOf(
            Stock("BGLWW", "0.31", true, 287.5),
            Stock("LCFY", "8.3", true, 228.0632),
            Stock("BGL", "62.5", true, 184.4788),
            Stock("NXLIW", "0.07", true, 132.5581),
            Stock("RCT", "3.24", true, 128.169),
            Stock("SERA", "4.09", true, 106.5657),
            Stock("JVSAU", "12.01", true, 73.0548),
            Stock("AMIX", "2.01", true, 60.8),
            Stock("TGE+", "0.5568", true, 54.6667),
            Stock("RILYP", "2.33", true, 47.4684),
            Stock("LIDRW", "0.0751", true, 44.9807),
            Stock("STSSW", "0.032", true, 44.1441),
            Stock("PSFE+", "0.0198", true, 43.4783),
            Stock("POLEW", "0.26", true, 42.7787),
            Stock("SOND", "2.27", true, 41.875),
            Stock("CREVW", "0.0249", true, 41.4773),
            Stock("ODYS", "7.01", true, 41.0463),
            Stock("FVNNR", "0.17", true, 40.3799),
            Stock("SVRE", "3.19", true, 39.3013),
            Stock("EYEN", "10.88", true, 37.8961)
        ),

        topLosers = listOf(
            Stock("LIMNW", "0.175", false, -51.3889),
            Stock("CLNNW", "0.022", false, -50.0),
            Stock("DAIC", "8.67", false, -47.2307),
            Stock("OTRK", "0.5534", false, -43.1536),
            Stock("LIMN", "9.48", false, -42.8916),
            Stock("GNLN", "3.08", false, -41.3479),
            Stock("BARK+", "0.012", false, -40.8867),
            Stock("PMNT", "0.252", false, -40.566),
            Stock("UKOMW", "0.0056", false, -40.4255),
            Stock("WFF", "3.99", false, -39.9096),
            Stock("OUSTW", "0.032", false, -38.4615),
            Stock("BULLZ", "0.82", false, -38.3459),
            Stock("HSDT", "0.211", false, -37.9229),
            Stock("ACHV", "2.23", false, -36.4672),
            Stock("OST", "0.3497", false, -36.4182),
            Stock("KLTO", "0.7201", false, -36.2743),
            Stock("AEMD", "1.25", false, -35.567),
            Stock("CHEB+", "0.0526", false, -35.5392),
            Stock("LEXXW", "0.11", false, -35.2941),
            Stock("ATIIW", "0.28", false, -35.1852)
        ),

        mostActivelyTraded = listOf(
            Stock("GVH", "0.133", false, -25.4902),
            Stock("BBAI", "5.855", true, 0.9483),
            Stock("NVDA", "157.75", true, 1.7611),
            Stock("HCTI", "0.0231", false, -12.8302),
            Stock("LCID", "2.12", false, -3.6364),
            Stock("PLTR", "130.74", false, -9.3657),
            Stock("SOXS", "7.88", true, 0.1271),
            Stock("NU", "13.26", false, -0.9709),
            Stock("F", "10.805", true, 1.6463),
            Stock("OPEN", "0.5584", true, 3.7918),
            Stock("TSLL", "12.27", false, -1.3666),
            Stock("OST", "0.3497", false, -36.4182),
            Stock("AUR", "5.17", false, -4.2593),
            Stock("DNN", "1.785", false, -2.9891),
            Stock("BURU", "0.3365", false, -1.0294),
            Stock("NKE", "72.07", true, 15.2382),
            Stock("SOXL", "25.09", false, -0.4365),
            Stock("QS", "6.615", false, -13.5294),
            Stock("AMZN", "223.3", true, 2.8464),
            Stock("GOOGL", "178.53", true, 2.8754)
        )
    )

}